package fr.aws.lambdastore.variant.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VariantPayload {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("product_id")
    private Long product_id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("price")
    private String price;
    @JsonProperty("sku")
    private String sku;
    @JsonProperty("position")
    private int position;
    @JsonProperty("inventory_policy")
    private String inventory_policy;
    @JsonProperty("fulfillment_service")
    private String fulfillment_service;
    @JsonProperty("inventory_management")
    private String inventory_management;
    @JsonProperty("option1")
    private String option1;
    @JsonProperty("option2")
    private String option2;
    @JsonProperty("created_at")
    private Timestamp created_at;
    @JsonProperty("updated_at")
    private Timestamp updated_at;
    @JsonProperty("taxable")
    private Boolean taxable;
    @JsonProperty("grams")
    private Long grams;
    @JsonProperty("weight")
    private Long weight;
    @JsonProperty("weight_unit")
    private String weight_unit;
    @JsonProperty("inventory_item_id")
    private Long inventory_item_id;
    @JsonProperty("inventory_quantity")
    private int inventory_quantity;
    @JsonProperty("old_inventory_quantity")
    private int old_inventory_quantity;
    @JsonProperty("requires_shipping")
    private Boolean requires_shipping;
    @JsonProperty("admin_graphql_api_id")
    private String admin_graphql_api_id;

}
