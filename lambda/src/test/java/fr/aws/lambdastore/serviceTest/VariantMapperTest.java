package fr.aws.lambdastore.serviceTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.aws.lambdastore.product.model.ProductPayload;
import fr.aws.lambdastore.variant.model.Variant;
import fr.aws.lambdastore.variant.model.VariantPayload;
import fr.aws.lambdastore.variant.service.VariantMapperImpl;

import java.io.File;
import java.io.IOException;
import java.util.Set;


//@SpringBootTest
public class VariantMapperTest {
//    @Autowired
//    VariantMapperImpl variantMapper;
//
//    @Test
//    public void shouldReturnVariant() throws IOException {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        ProductPayload productPayload = objectMapper.readValue(new File("src/test/resources/productPayload.json"), ProductPayload.class);
//        Set<VariantPayload> variantPayloads = productPayload.getVariants();
//        Set<Variant> variants = variantMapper.map(variantPayloads);
//
//        assertThat(variants.size()).isEqualTo(24);
//    }
}
