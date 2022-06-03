package fr.aws.lambdastore.variant.service;

import fr.aws.lambdastore.variant.model.Variant;
import fr.aws.lambdastore.variant.model.VariantPayload;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class VariantMapperImpl implements VariantMapper {

    @Inject
    public VariantMapperImpl() { }

    @Override
    public Set<Variant> map(Set<VariantPayload> variantPayloads) {

        Set<Variant> variants = new HashSet<>();

        if (variantPayloads != null) {

            for (VariantPayload variantPayload : variantPayloads) {
                variants.add(variantPayloadToVariant(variantPayload));
            }
        }

        return variants;
    }

    private Variant variantPayloadToVariant(VariantPayload variantPayload) {

        Variant variant = new Variant();

        if(variantPayload != null) {

            variant.setPrice(variantPayload.getPrice());
            variant.setShopifyVariantId(variantPayload.getId());
            variant.setSku(variantPayload.getSku());
            variant.setWeightUnit(variantPayload.getWeight_unit());
            variant.setWeight(variantPayload.getWeight());
            variant.setAvailableInventory(variantPayload.getInventory_quantity());
            variant.setTitle(variantPayload.getTitle());
        }

        return variant;
    }
}
