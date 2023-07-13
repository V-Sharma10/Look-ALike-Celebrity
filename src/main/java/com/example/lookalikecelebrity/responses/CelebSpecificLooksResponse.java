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
public class CelebSpecificLooksResponse {

    @JsonProperty("look_id")
    private Long lookId;

    @JsonProperty("look_name")
    private String lookName;

    @JsonProperty("celeb_id")
    private Long celebId;

    @JsonProperty("look_images")
    private List<String> lookImages;

}
