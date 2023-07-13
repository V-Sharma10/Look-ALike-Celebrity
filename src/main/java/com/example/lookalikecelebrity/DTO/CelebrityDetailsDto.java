package com.example.lookalikecelebrity.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CelebrityDetailsDto {

    @JsonProperty("celeb_id")
    private Long celebId;

    @JsonProperty("celeb_name")
    private String celebName;

    @JsonProperty("celeb_hero_url")
    private String celebHeroUrl;


}
