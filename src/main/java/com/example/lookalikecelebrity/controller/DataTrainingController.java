package com.example.lookalikecelebrity.controller;

import com.example.lookalikecelebrity.service.DataTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class DataTrainingController {

    @Autowired
    DataTrainingService dataTrainingService;

    @PostMapping("/train")
    public void trainData(@RequestParam("training_data_csv") MultipartFile trainingDataCsv) throws IOException {
        dataTrainingService.trainData(trainingDataCsv);
    }


}
