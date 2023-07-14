package com.example.lookalikecelebrity.requests;

import com.example.lookalikecelebrity.entity.enums.FeedType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductSearchRequest {
    @JsonProperty("cursor")
    private String cursor;
    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("filter")
    private Filter filter;
    @JsonProperty("search_session_id")
    private String searchSessionId;
    @JsonProperty("retry_count")
    private Integer retryCount;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Filter {

        @JsonProperty("type")
        private FeedType type;
        @JsonProperty("session_state")
        private String sessionState;
        @JsonProperty("image_url")
        private String imageUrl;
        @JsonProperty("query")
        private String query;
        @JsonProperty("search_id")
        private String searchId;

    }

}
