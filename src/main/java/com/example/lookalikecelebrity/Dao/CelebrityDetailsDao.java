package com.example.lookalikecelebrity.Dao;

import com.example.lookalikecelebrity.DTO.CelebrityDetailsDto;
import com.example.lookalikecelebrity.entity.CelebrityDetails;
import com.example.lookalikecelebrity.repository.CelebrityDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CelebrityDetailsDao {

    @Autowired
    CelebrityDetailsRepository celebrityDetailsRepository;

    public List<CelebrityDetailsDto> getCelebrityDetails(List<String> celebNames) {
        List<CelebrityDetails> celebrityDetailsList = celebrityDetailsRepository.findAllByCelebrityName();

        return transformToProductDetailsDto(celebrityDetailsList);
    }

    private List<CelebrityDetailsDto> transformToProductDetailsDto(List<CelebrityDetails> celebrityDetailsList) {
        List<CelebrityDetailsDto> celebrityDetailsDtoList = new ArrayList<>();
        for(CelebrityDetails celebrityDetails : celebrityDetailsList) {
            celebrityDetailsDtoList.add(CelebrityDetailsDto.builder()
                            .celebHeroUrl(celebrityDetails.getCelebHeroImageUrl())
                            .celebId(celebrityDetails.getId())
                            .celebName(celebrityDetails.getCelebrityName())
                    .build());
        }
        return celebrityDetailsDtoList;
    }


}
