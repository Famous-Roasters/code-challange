package fr.aws.lambdastore.product.service;

import fr.aws.lambdastore.product.model.Product;
import fr.aws.lambdastore.product.model.ProductPayload;
import fr.aws.lambdastore.utils.HibernateDataSource;
import fr.aws.lambdastore.variant.model.Variant;
import fr.aws.lambdastore.variant.service.VariantMapperImpl;
import fr.aws.lambdastore.vendor.model.Vendor;
import fr.aws.lambdastore.vendor.service.VendorService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

@Singleton
public class ProductMapper {
    private static final String PAYLOAD_ID_PARAM = "shopifyProductId";

    VendorService mVendorService;
    VariantMapperImpl mVariantMapper;

    @Inject
    public ProductMapper(VendorService vendorService, VariantMapperImpl variantMapper) {
        this.mVendorService = vendorService;
        this.mVariantMapper = variantMapper;
    }

    public void setVariantMapper(VariantMapperImpl mVariantMapper) {
        this.mVariantMapper = mVariantMapper;
    }

    public void setVendorService(VendorService mVendorService) {
        this.mVendorService = mVendorService;
    }

    public void mapToEntityModel(ProductPayload productPayload, String url) {

        Set<Variant> variantSet = mVariantMapper.map(productPayload.getVariants());
        Vendor vendor = mVendorService.getVendorByName(productPayload.getVendor());
        Product product = new Product();

        HibernateDataSource<Product> dataSource = new HibernateDataSource<>();
        dataSource.prepareQuery("from Product p where p.shopifyProductId=:shopifyProductId", product);
        dataSource.getQuery().setParameter(PAYLOAD_ID_PARAM, productPayload.getId());
        product = dataSource.executeReadQuery();

        if (product == null){
            product = new Product();
        }

        product.setTitle(productPayload.getTitle());
        product.setDescription(productPayload.getBody_html());
        product.setStatus(productPayload.getStatus());
        product.setCreatedAt(productPayload.getCreated_at());
        product.setShopifyProductId(productPayload.getId());
        product.setHandle(productPayload.getHandle());
        product.setTags(productPayload.getTags());
        product.setProductType(productPayload.getProduct_type());
        product.setVariants(variantSet);
        product.setUrl(url);
        product.setInsertedAt(Timestamp.from(Instant.now()));
        product.setVendor(vendor);

        dataSource.executeWriteQuery(product);
        dataSource.close();
    }
}
