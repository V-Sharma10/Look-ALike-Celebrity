package com.example.lookalikecelebrity.service;

import com.example.lookalikecelebrity.DTO.CelebrityDetailsDto;
import com.example.lookalikecelebrity.httpHandlers.AzurePredictionHandler;
//import com.example.lookalikecelebrity.responses.CelebLookAlikeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<CelebrityDetailsDto> getCelebrityLookAlikeImages(InputStream inputStream) throws Exception {
        return azurePredictionHandler.getCelebLookAlikeResponse(inputStream);
    }
}
