package com.example.lookalikecelebrity.controller;

import com.example.lookalikecelebrity.service.DetectFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DetectFaceController {

    @Autowired
    DetectFaceService detectFaceService;

    @PostMapping("/detect")
    public  void detectFace(@RequestParam("image1") MultipartFile image1,
                            @RequestParam("image2") MultipartFile image2) {
        detectFaceService.detectFace(image1,image2);
    }

}
