package fr.aws.lambdastore.variant.service;

import fr.aws.lambdastore.variant.model.Variant;
import fr.aws.lambdastore.variant.model.VariantPayload;
import java.util.Set;

public interface VariantMapper {
    Set<Variant> map(Set<VariantPayload> variantPayloads);
}
