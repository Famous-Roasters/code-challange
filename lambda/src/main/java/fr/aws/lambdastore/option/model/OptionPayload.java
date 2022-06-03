package fr.aws.lambdastore.option.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OptionPayload {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("product_id")
    private Long product_id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("position")
    private int position;
    @JsonProperty("values")
    private Set<String> values;
}
