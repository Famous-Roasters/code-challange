package fr.aws.lambdastore.handlerTest;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import fr.aws.lambdastore.Handler;
import fr.aws.lambdastore.product.model.Product;
import fr.aws.lambdastore.product.model.ProductPayload;
import fr.aws.lambdastore.product.service.ProductMapper;
import fr.aws.lambdastore.variant.service.VariantMapperImpl;
import fr.aws.lambdastore.vendor.service.VendorService;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HandlerTest {

    @Test
    public void mapperShouldReturnProductPayload() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode node = objectMapper.readValue(new File("src/test/resources/product.json"), JsonNode.class);
        node.get("detail").get("payload");

        ProductPayload productPayload = objectMapper.
                readValue(node.get("detail").get("payload").toString(),ProductPayload.class);

        Assert.assertEquals(productPayload.getId(),Long.valueOf("7655275561176"));
    }
    @Test(expected = InvalidFormatException.class)
    public void givenJsonHasUnknownValues_whenDeserializing_thenException() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Product readValue = mapper.readValue(new File("src/test/resources/productWithUnknownFields.json"), Product.class);

        assertNotNull(readValue);
    }
    @Test
    public void handlerShouldMap() throws IOException {
        Handler mHandler = new Handler();
        ProductMapper mMapper = new ProductMapper(new VendorService(), new VariantMapperImpl());
        mHandler.setProductMapper(mMapper);

        Context context = new TestContext();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object > node = objectMapper.readValue(new File("src/test/resources/product.json"), Map.class);
        String result = mHandler.handleRequest(node, context);
        System.out.println(result);
        assertEquals(result,"Payload received!");
    }
}
