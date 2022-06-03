package fr.aws.lambdastore.product.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.aws.lambdastore.image.model.ImagePayload;
import fr.aws.lambdastore.option.model.OptionPayload;
import fr.aws.lambdastore.variant.model.VariantPayload;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductPayload {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("body_html")
    private String body_html;
    @JsonProperty("vendor")
    private String vendor;
    @JsonProperty("product_type")
    private String product_type;
    @JsonProperty("created_at")
    private Timestamp created_at;
    @JsonProperty("handle")
    private String handle;
    @JsonProperty("updated_at")
    private Timestamp updated_at;
    @JsonProperty("published_at")
    private Timestamp published_at;
    @JsonProperty("status")
    private String status;
    @JsonProperty("published_scope")
    private String published_scope;
    @JsonProperty("tags")
    private String tags;
    @JsonProperty("admin_graphql_api_id")
    private String admin_graphql_api_id;
    @JsonProperty("variants")
    private Set<VariantPayload> variants;
    @JsonProperty("options")
    private Set<OptionPayload> options;
    @JsonProperty("images")
    private Set<ImagePayload> images;
    @JsonProperty("image")
    private ImagePayload image;
}
