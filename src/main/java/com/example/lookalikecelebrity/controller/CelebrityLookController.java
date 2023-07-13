package com.example.lookalikecelebrity.controller;

import com.example.lookalikecelebrity.DTO.CelebrityDetailsDto;
import com.example.lookalikecelebrity.service.CelebrityLookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@RestController
public class CelebrityLookController {

    @Autowired
    CelebrityLookService celebrityLookService;

    @PostMapping(value = "/api/v1/celeb-look-alike", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<CelebrityDetailsDto>> getCelebrityLookAlikeImages(@RequestParam("image1") MultipartFile multipartFile) throws Exception {
        InputStream inputStream = multipartFile.getInputStream();

        return ResponseEntity.ok(celebrityLookService.getCelebrityLookAlikeImages(multipartFile));
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
