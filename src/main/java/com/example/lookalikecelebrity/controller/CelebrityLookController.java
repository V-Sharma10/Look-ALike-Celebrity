package com.example.lookalikecelebrity.controller;

import com.example.lookalikecelebrity.DTO.CelebrityDetailsDto;
//import com.example.lookalikecelebrity.responses.CelebLookAlikeResponse;
import com.example.lookalikecelebrity.responses.AzurePredictionResponse;
import com.example.lookalikecelebrity.requests.AzurePredictionRequest;
import com.example.lookalikecelebrity.service.CelebrityLookService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController("/api/v1/celeb-look-alike")
public class CelebrityLookController {

    @Autowired
    CelebrityLookService celebrityLookService;

    @PostMapping("")
    public ResponseEntity<List<CelebrityDetailsDto>> getCelebrityLookAlikeImages(@RequestBody MultipartFile multipartFile) throws Exception {
        InputStream inputStream = multipartFile.getInputStream();
        byte[] binaryData = StreamUtils.copyToByteArray(inputStream);

        return ResponseEntity.ok(celebrityLookService.getCelebrityLookAlikeImages(inputStream));
    }


//    @PostMapping("")
//    public ResponseEntity<AzurePredictionResponse> getCelebrityPredictions(@RequestHeader("Prediction-Key") @NonNull String predictionKey,
//                                                                           @Valid @RequestBody AzurePredictionRequest predictionRequest) {
//
//        try {
//            return ResponseEntity.ok(celebrityLookService.getCelebrityLookAlikePredictions());
//        } catch (Exception e){
//            return ResponseEntity.status(500).build();
//        }
//
//    }

}
