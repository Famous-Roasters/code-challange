package fr.aws.lambdastore.image.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ImagePayload {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("product_id")
    private Long product_id;
    @JsonProperty("position")
    private int position;
    @JsonProperty("created_at")
    private Timestamp created_at;
    @JsonProperty("updated_at")
    private Timestamp updated_at;
    @JsonProperty("width")
    private int width;
    @JsonProperty("height")
    private int height;
    @JsonProperty("src")
    private String src;
    @JsonProperty("variant_ids")
    private List<Long> variant_ids;
    @JsonProperty("admin_graphql_api_id")
    private String admin_graphql_api_id;
}
