package com.example.lookalikecelebrity.Dao;

import com.example.lookalikecelebrity.DTO.LookDetailsDto;
import com.example.lookalikecelebrity.entity.LookDetails;
import com.example.lookalikecelebrity.repository.LookDetailsRepository;
import com.example.lookalikecelebrity.responses.CelebSpecificLooksResponse;
import com.example.lookalikecelebrity.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CelebSpecificLooksDao {

    @Autowired
    LookDetailsRepository lookDetailsRepository;

    public CelebSpecificLooksResponse getCelebSpecificLooksResponse(Long celebId, Long lookId) {

        LookDetails lookDetails = lookDetailsRepository.getLookDetailsByCelebIdAndAndLookId(celebId,lookId);

        return CelebSpecificLooksResponse.builder()
                .celebId(lookDetails.getCelebId())
                .lookId(lookDetails.getLookId())
                .lookName(lookDetails.getLookName())
                .lookImages(CommonUtils.convertStringToList(lookDetails.getLookUrlPerCelebList()))
                .build();
    }

}
