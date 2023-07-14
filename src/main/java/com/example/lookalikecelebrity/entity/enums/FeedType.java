package com.example.lookalikecelebrity.entity.enums;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(
        ignoreUnknown = true
)
public enum FeedType {
    @JsonProperty("visual_search")
    VISUAL_SEARCH("visual_search", 3);
    private final String type;
    private final Integer shortValue;

    private FeedType(String type, Integer shortValue) {
        this.type = type;
        this.shortValue = shortValue;
    }

    public String getType() {
        return this.type;
    }

    public Integer getShortValue() {
        return this.shortValue;
    }


}

