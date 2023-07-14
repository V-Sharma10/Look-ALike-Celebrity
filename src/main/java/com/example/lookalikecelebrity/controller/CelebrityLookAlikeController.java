package com.example.lookalikecelebrity.controller;

import com.example.lookalikecelebrity.DTO.CelebrityDetailsDto;
import com.example.lookalikecelebrity.DTO.LookDetailsDto;
import com.example.lookalikecelebrity.responses.CelebSpecificLooksResponse;
import com.example.lookalikecelebrity.responses.ProductSearchResponse;
import com.example.lookalikecelebrity.service.CelebrityLookAlikeService;
import com.example.lookalikecelebrity.service.VisualSearchApiIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@RequestMapping("/api/v1/celeb-look-alike")
@RestController
public class CelebrityLookAlikeController {

    @Autowired
    CelebrityLookAlikeService celebrityLookAlikeService;

    @Autowired
    VisualSearchApiIntegration visualSearchApiIntegration;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<CelebrityDetailsDto>> getCelebrityLookAlikeImages(@RequestParam("image1") MultipartFile multipartFile) throws Exception {
        InputStream inputStream = multipartFile.getInputStream();

        return ResponseEntity.ok(celebrityLookAlikeService.getCelebrityLookAlikeImages(multipartFile));
    }

    @PostMapping(value = "/looks/{celeb_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LookDetailsDto>> getCelebLooks(@PathVariable("celeb_id") Long celebId) {
        return ResponseEntity.ok(celebrityLookAlikeService.getCelebLooks(celebId));
    }

    @PostMapping(value = "/{celeb_id}/look/{look_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CelebSpecificLooksResponse> getCelebSpecificLooksResponse(@PathVariable("celeb_id") Long celebId,
                                                                                    @PathVariable("look_id") Long lookId) {
        return ResponseEntity.ok(celebrityLookAlikeService.getCelebSpecificLooksResponse(celebId, lookId));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductSearchResponse>> getProducts(@RequestParam("url") String url) {
        return ResponseEntity.ok(visualSearchApiIntegration.getVisualSearchResponse(url));
    }

}
