package fr.aws.lambdastore.di;

import dagger.Module;
import dagger.Provides;
import fr.aws.lambdastore.product.service.ProductMapper;
import fr.aws.lambdastore.variant.service.VariantMapperImpl;
import fr.aws.lambdastore.vendor.service.VendorService;

import javax.inject.Singleton;

@Module
public class MainModule {

    @Provides
    @Singleton
    ProductMapper provideShopify(VendorService service, VariantMapperImpl variantMapper) {
        return new ProductMapper(service, variantMapper);
    }

    @Provides
    @Singleton
    VendorService provideVendorService() {
        return new VendorService();
    }

    @Provides
    @Singleton
    VariantMapperImpl provideVariantMapperImpl() {
        return new VariantMapperImpl();
    }

}
