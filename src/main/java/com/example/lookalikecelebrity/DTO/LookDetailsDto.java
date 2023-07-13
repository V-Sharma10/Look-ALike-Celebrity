package com.example.lookalikecelebrity.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LookDetailsDto {

    @JsonProperty("looks_url_per_celeb_list")
    private List<String> lookUrlPerCelebList;

    @JsonProperty("celeb_id")
    private Long celebId;

    @JsonProperty("look_id")
    private Long lookId;

    @JsonProperty("look_url_per_celeb_hero_image")
    private String lookUrlPerCelebHeroImage;

    @JsonProperty("look_name")
    private String lookName;

}
