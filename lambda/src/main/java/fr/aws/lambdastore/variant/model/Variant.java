package fr.aws.lambdastore.variant.model;

import fr.aws.lambdastore.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "Variant")
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "package_size")
    private String packageSize;
    @Column(name = "weight")
    private Long weight;
    @Column(name = "gross_price")
    private double grossPrice;
    @Column(name = "grind_type")
    private String grindType;
    @Column(name = "available_inventory")
    private Integer availableInventory;
    @Column(name = "barcode")
    private String barcode;
    @Column(name = "shopify_variant_id")
    private Long shopifyVariantId;
    @Column(name = "sku")
    private String sku;
    @Column(name = "weight_unit")
    private String weightUnit;
    @Column(name = "price")
    private String price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
