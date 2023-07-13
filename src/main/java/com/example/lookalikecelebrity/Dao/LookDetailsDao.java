package com.example.lookalikecelebrity.Dao;

import com.example.lookalikecelebrity.DTO.LookDetailsDto;
import com.example.lookalikecelebrity.entity.LookDetails;
import com.example.lookalikecelebrity.repository.LookDetailsRepository;
import com.example.lookalikecelebrity.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LookDetailsDao {

    @Autowired
    LookDetailsRepository lookDetailsRepository;

    public List<LookDetailsDto> getLooksDetails(Long celebId) {
        List<LookDetails> lookDetailsList = lookDetailsRepository.findAllByCelebId(celebId);
        return transformToLookDetailsDtoList(lookDetailsList);
    }

    private List<LookDetailsDto> transformToLookDetailsDtoList(List<LookDetails> lookDetailsList) {
        List<LookDetailsDto> lookDetailsDtoList = new ArrayList<>();
        for(LookDetails lookDetails: lookDetailsList){
            lookDetailsDtoList.add(LookDetailsDto.builder()
                    .lookId(lookDetails.getLookId())
                    .lookName(lookDetails.getLookName())
                    .lookUrlPerCelebHeroImage(lookDetails.getLookUrlPerCelebHeroImage())
                    .celebId(lookDetails.getCelebId())
                    .build());
        }
        return lookDetailsDtoList;
    }


}
