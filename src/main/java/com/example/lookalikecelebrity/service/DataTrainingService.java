package com.example.lookalikecelebrity.service;

import com.example.lookalikecelebrity.utils.CommonUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DataTrainingService {

    public void trainData(MultipartFile trainingDataCsv) throws IOException {
        List<String[]> rows = CommonUtils.readFile(trainingDataCsv);
        rows.forEach(System.out::println);
    }

}
