package com.example.lookalikecelebrity.controller;

import com.example.lookalikecelebrity.service.DetectFaceService;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;


@RestController
public class ImageScoreController {

    @Autowired
    DetectFaceService detectFaceService;

    @PostMapping("/compare")
    public double compareImages(@RequestParam("image1") MultipartFile image1,
                                @RequestParam("image2") MultipartFile image2) {
        try {
            // Convert MultipartFiles to Mat images using JavaCV
            Mat mat1 = convertMultipartFileToMat(image1);
            Mat mat2 = convertMultipartFileToMat(image2);

            // Calculate similarity score
            double score = calculateSimilarityScore(mat1, mat2);

            // Release resources
            mat1.release();
            mat2.release();

            return score;
        } catch (Exception e) {
            e.printStackTrace();
            return -1.0; // or handle the exception appropriately
        }
    }

    private Mat convertMultipartFileToMat(MultipartFile multipartFile) throws IOException {
        // Load MultipartFile as BufferedImage
        BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
        return bufferedImage2Mat(bufferedImage);
    }

    public static Mat bufferedImage2Mat(BufferedImage image) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        return Imgcodecs.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Imgcodecs.IMREAD_UNCHANGED);
    }

    public double calculateSimilarityScore(Mat image1, Mat image2) {
        Mat image1Gray = new Mat();
        Mat image2Gray = new Mat();
        Imgproc.cvtColor(image1, image1Gray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(image2, image2Gray, Imgproc.COLOR_BGR2GRAY);

        MatOfFloat histRange = new MatOfFloat(0f, 256f);
        MatOfInt histSize = new MatOfInt(256);
        Mat hist1 = new Mat();
        Mat hist2 = new Mat();

        Imgproc.calcHist(
                Collections.singletonList(image1Gray),
                new MatOfInt(0),
                new Mat(),
                hist1,
                histSize,
                histRange
        );

        Imgproc.calcHist(
                Collections.singletonList(image2Gray),
                new MatOfInt(0),
                new Mat(),
                hist2,
                histSize,
                histRange
        );

        double score = Imgproc.compareHist(hist1, hist2, Imgproc.HISTCMP_CORREL);
        return score;
    }


}
