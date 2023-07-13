package com.example.lookalikecelebrity.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AzurePredictionResponse {

    @JsonProperty("predictions")
    private List<Prediction> predictions;

    @Data
    @Builder
    @AllArgsConstructor
    public static class Prediction {

        @JsonProperty("probability")
        private Double probability;

        @JsonProperty("tagName")
        private String tagName;
    }

}
