package com.example.lookalikecelebrity.service;

import com.example.lookalikecelebrity.DTO.CelebrityDetailsDto;
import com.example.lookalikecelebrity.DTO.LookDetailsDto;
import com.example.lookalikecelebrity.Dao.CelebSpecificLooksDao;
import com.example.lookalikecelebrity.Dao.LookDetailsDao;
import com.example.lookalikecelebrity.entity.LookDetails;
import com.example.lookalikecelebrity.httpHandlers.AzurePredictionHandler;
//import com.example.lookalikecelebrity.responses.CelebLookAlikeResponse;
import com.example.lookalikecelebrity.repository.LookDetailsRepository;
import com.example.lookalikecelebrity.responses.CelebSpecificLooksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CelebrityLookAlikeService {

    @Autowired
    AzurePredictionHandler azurePredictionHandler;

    @Autowired
    LookDetailsDao lookDetailsDao;

    @Autowired
    CelebSpecificLooksDao celebSpecificLooksDao;


    public List<CelebrityDetailsDto> getCelebrityLookAlikeImages(MultipartFile multipartFile) throws Exception {
        return azurePredictionHandler.getCelebLookAlikeResponse(multipartFile);
    }

    public List<LookDetailsDto> getCelebLooks(Long celebId) {
        return lookDetailsDao.getLooksDetails(celebId);
    }

    public CelebSpecificLooksResponse getCelebSpecificLooksResponse(Long celebId,Long lookId) {
        return celebSpecificLooksDao.getCelebSpecificLooksResponse(celebId,lookId);
    }

}
