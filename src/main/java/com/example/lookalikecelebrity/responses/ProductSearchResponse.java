package com.example.lookalikecelebrity.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductSearchResponse {

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("product_url")
    private String productUrl;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("product_price")
    private String productPrice;
}
