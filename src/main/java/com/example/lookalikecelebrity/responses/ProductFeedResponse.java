package com.example.lookalikecelebrity.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductFeedResponse {

    @JsonProperty("products")
    public List<ProductCatalogView> productCatalogViews;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ProductCatalogView {

        @JsonProperty("product")
        ProductSupplierViewV2 productSupplierView;

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class ProductSupplierViewV2 {

            @JsonProperty("id")
            private Integer id;

            @JsonProperty("name")
            private String name;

            @JsonProperty("images")
            private List<String> images;

            @JsonProperty("mrp")
            private Integer mrpPrice;

        }

    }


}
