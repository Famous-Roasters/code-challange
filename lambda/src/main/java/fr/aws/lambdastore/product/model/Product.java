package fr.aws.lambdastore.product.model;

import fr.aws.lambdastore.variant.model.Variant;
import fr.aws.lambdastore.vendor.model.Vendor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @Column(name = "title")
    private String title;
    @Column(name = "description",columnDefinition = "TEXT")
    private String description;
    @Column(name = "status")
    private String status;
    @Column(name = "shopify_product_id")
    private Long shopifyProductId;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "handle")
    private String handle;
    @Column(name = "product_type")
    private String productType;
    @Column(name = "tags")
    private String tags;
    @Column(name = "url", columnDefinition = "TEXT")
    private String url;
    @Column(name = "inserted_at")
    private Timestamp insertedAt;


    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor_id", referencedColumnName = "id")
    private Vendor vendor;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "variant_id", referencedColumnName = "id")
    private Set<Variant> variants;
}
