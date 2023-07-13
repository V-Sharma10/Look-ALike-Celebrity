package com.example.lookalikecelebrity.service;

import com.example.lookalikecelebrity.DTO.CelebrityDetailsDto;
import com.example.lookalikecelebrity.httpHandlers.AzurePredictionHandler;
//import com.example.lookalikecelebrity.responses.CelebLookAlikeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class CelebrityLookService {

    @Autowired
    AzurePredictionHandler azurePredictionHandler;

//    public PredictionResponse getCelebrityLookAlikePredictions() {
//
//        return
//
//    }

    public List<CelebrityDetailsDto> getCelebrityLookAlikeImages(MultipartFile multipartFile) throws Exception {
        return azurePredictionHandler.getCelebLookAlikeResponse(multipartFile);
    }
}
