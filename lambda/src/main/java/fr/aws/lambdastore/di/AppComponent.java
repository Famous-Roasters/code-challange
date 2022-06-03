package fr.aws.lambdastore.di;

import dagger.Component;
import fr.aws.lambdastore.product.service.ProductMapper;
import fr.aws.lambdastore.variant.service.VariantMapperImpl;
import fr.aws.lambdastore.vendor.service.VendorService;

import javax.inject.Singleton;

@Singleton
@Component(modules = { MainModule.class })
public interface AppComponent {

    ProductMapper mapper();

    VendorService vendorService();

    VariantMapperImpl variantMapper();

}
