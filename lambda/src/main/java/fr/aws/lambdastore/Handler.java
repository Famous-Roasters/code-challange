package fr.aws.lambdastore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.StorageClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.aws.lambdastore.di.Injector;
import fr.aws.lambdastore.product.model.ProductPayload;
import fr.aws.lambdastore.product.service.ProductMapper;
import javax.inject.Singleton;
import java.io.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;

@Singleton
public class Handler implements RequestHandler<Map<String, Object>, String> {

    private ProductMapper productMapper;

    private final String accessKey =System.getenv("ACCESS_KEY");

    private final String secretKey=System.getenv("SECRET_KEY");

    private final String bucketName="shopify-product-events-"+System.getenv("S3_BUCKET_ENV");


    public Handler() {
        productMapper = Injector.getInjector().mapper();

    }

    @Override
    public String handleRequest(Map<String, Object> event, Context context) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        LambdaLogger logger = context.getLogger();
        logger.log("EVENT: " + gson.toJson(event));
        String url = "";
        try {
            if(productMapper == null){
                throw new RuntimeException("product mapper is null");
            }
            JsonNode node = objectMapper.readValue(gson.toJson(event),JsonNode.class);
            node.get("detail").get("payload");
            ProductPayload productPayload = objectMapper.readValue(node.get("detail").get("payload").toString(),ProductPayload.class);
            logger.log("id:"+ productPayload.getId());

            url = storeDataToS3(gson.toJson(event), logger, productPayload.getId(), url);

            productMapper.mapToEntityModel(productPayload, url);

        } catch (JsonProcessingException e) {
            System.out.println("exception:"+ e.getMessage());
            e.printStackTrace();
        }
        return "Payload received!";
    }

    private String storeDataToS3(String eventPayload, LambdaLogger logger, Long id, String url) {

        Timestamp ts = Timestamp.from(Instant.now());
        String keyName = id.toString()+"-"+ts;

        AWSCredentials credentials = new BasicAWSCredentials(
                accessKey,
                secretKey
        );

        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            ObjectOutputStream serializer = new ObjectOutputStream(bytes);
            serializer.writeObject(eventPayload);
            serializer.flush();
            serializer.close();

            InputStream stream = new ByteArrayInputStream(bytes.toByteArray());
            ObjectMetadata metadata = new ObjectMetadata();

            metadata.setContentType("application/json");
            metadata.setContentDisposition("attachment; filename=\"" + keyName);
            metadata.setContentLength(bytes.size());
            logger.log(String.valueOf(bytes.size()));

            PutObjectRequest put = new PutObjectRequest(bucketName, keyName,
                    stream, metadata);
            put.setStorageClass(StorageClass.ReducedRedundancy);
            put.setMetadata(metadata);
            put.setSdkRequestTimeout(300000);
            put.withSdkClientExecutionTimeout(300000);
            s3client.putObject(put);
            url = s3client.getUrl(bucketName, keyName).toString();
        } catch (IOException | AmazonServiceException e){
            e.printStackTrace();
        }
        return url;
    }

    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }
}
