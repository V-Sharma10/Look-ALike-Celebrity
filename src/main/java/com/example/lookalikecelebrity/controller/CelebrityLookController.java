package com.example.lookalikecelebrity.controller;

import com.example.lookalikecelebrity.DTO.CelebrityDetailsDto;
import com.example.lookalikecelebrity.service.CelebrityLookService;
import com.example.lookalikecelebrity.service.VisualSearchApiIntegration;
import com.meesho.storefront.clients.dtos.response.ProductFeedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController("/api/v1/celeb-look-alike")
public class CelebrityLookController {

    @Autowired
    CelebrityLookService celebrityLookService;

    @Autowired
    VisualSearchApiIntegration visualSearchApiIntegration;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<CelebrityDetailsDto>> getCelebrityLookAlikeImages(@RequestParam("image1") MultipartFile multipartFile) throws Exception {
        return ResponseEntity.ok(celebrityLookService.getCelebrityLookAlikeImages(multipartFile));
    }

    @PostMapping("/products")
    public ResponseEntity<ProductFeedResponse> getProducts(@RequestParam("url") String url) {
        return ResponseEntity.ok(visualSearchApiIntegration.getVisualSearchResponse(url));
    }
}
