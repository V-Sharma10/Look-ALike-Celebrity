package com.example.lookalikecelebrity.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CommonUtils {

    public static List<String[]> readFile (MultipartFile file) throws IOException {
        List<String[]> res = new ArrayList<>();

        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = file.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                String[] row = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                res.add(row);
            }
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(bufferedReader);
        }
        return res;
    }

}
