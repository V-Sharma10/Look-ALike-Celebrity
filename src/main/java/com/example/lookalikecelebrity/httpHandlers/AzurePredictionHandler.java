package com.example.lookalikecelebrity.httpHandlers;

import com.example.lookalikecelebrity.DTO.CelebrityDetailsDto;
import com.example.lookalikecelebrity.Dao.CelebrityDetailsDao;
import com.example.lookalikecelebrity.requests.AzurePredictionRequest;
import com.example.lookalikecelebrity.responses.AzurePredictionResponse;
//import com.example.lookalikecelebrity.responses.CelebLookAlikeResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AzurePredictionHandler {

    private static final String AZURE_PREDICTION_SERVICE_URL = "https://tthcustomv-prediction.cognitiveservices.azure.com/customvision/v3.0/Prediction/a8d14ac0-dd0d-4126-8cdd-841f2d93dfe3/classify/iterations/Iteration3/image";
    private static final String PREDICTION_KEY = "Prediction-Key";

    @Autowired
    private CelebrityDetailsDao celebrityDetailsDao;
    private final RestTemplate restTemplate = new RestTemplate();

    private final ObjectMapper mapper;

    public List<CelebrityDetailsDto> getCelebLookAlikeResponse(MultipartFile multipartFile) throws Exception {

        AzurePredictionResponse azurePredictionResponse = getPredictionsForImage(multipartFile);
        String gender = azurePredictionResponse.getPredictions().get(0).getTagName();
        Set<String> resultantCelebs = new HashSet<>();
        if(gender.equals("male")) {

            Set<AzurePredictionResponse.Prediction> predictions =  azurePredictionResponse.getPredictions().stream()
                    .filter(prediction -> getGenderMapList().get("male").contains(prediction.getTagName()))
                    .limit(4)
                    .collect(Collectors.toSet());

            predictions.forEach(prediction -> {
                resultantCelebs.add(prediction.getTagName());
            });
        } else {
            Set<AzurePredictionResponse.Prediction> predictions =  azurePredictionResponse.getPredictions().stream()
                    .filter(prediction -> getGenderMapList().get("female").contains(prediction.getTagName()))
                    .limit(4)
                    .collect(Collectors.toSet());

            predictions.forEach(prediction -> {
                resultantCelebs.add(prediction.getTagName());
            });
        }
        return celebrityDetailsDao.getCelebrityDetails(new ArrayList<>(resultantCelebs));
    }

    private AzurePredictionResponse getPredictionsForImage(MultipartFile multipartFile) throws Exception {
      return  mapper.readValue(getAzureResponseInString(multipartFile),
              new TypeReference<AzurePredictionResponse>() {}) ;
    }


    private Map<String, Set<String>> getGenderMapList() {
        Map<String, Set<String>> genderMap = new HashMap<>();
        genderMap.put("male", getActors());
        genderMap.put("female", getActresses());
        return genderMap;
    }

    private Set<String> getActors() {
        Set<String> actors = new HashSet<>();
        actors.add("aamir_khan");
        actors.add("ajay_devgan");
        actors.add("amitabh_bachchan");
        actors.add("govinda");
        actors.add("hrithik_roshan");
        actors.add("john_abraham");
        actors.add("kartik_aryan");
        actors.add("randeep_hooda");
        actors.add("ranveer_singh");
        actors.add("salman_khan");
        return actors;
    }


    private Set<String> getActresses() {
        Set<String> actresses = new HashSet<>();
        actresses.add("aishwarya_rai");
        actresses.add("jacqueline_fernandez");
        actresses.add("juhi_chawla");
        actresses.add("kangana_ranaut");
        actresses.add("kriti_kharbanda");
        actresses.add("kareena_kapoor");
        actresses.add("katrina_kaif");
        actresses.add("pooja_hegde");
        actresses.add("priyanka_chopra");
        actresses.add("richa_chaddha");
        return actresses;

    }

private String getAzureResponseInString(MultipartFile multipartFile) throws IOException {
        // Set the URL and headers
        String url = "https://tthcustomv-prediction.cognitiveservices.azure.com/customvision/v3.0/Prediction/a8d14ac0-dd0d-4126-8cdd-841f2d93dfe3/classify/iterations/Iteration3/image";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Prediction-Key", "5af8ab79cc5c4a7fa4d66e22582e0479");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        // Read file contents into byte array
        byte[] fileContent =  convertMultipartFileToByteArray(multipartFile);

        // Create a MultiValueMap to hold the request body
    ByteArrayResource resource = new ByteArrayResource(fileContent) {};

    // Build the request entity with the resource and headers
    HttpEntity<ByteArrayResource> requestEntity = new HttpEntity<>(resource, headers);

    // Make the POST request with RestTemplate
    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

    // Handle the response as needed
            if (response.getStatusCode() == HttpStatus.OK) {
                String responseBody = response.getBody();
                System.out.println("Response: " + responseBody);
                return responseBody;
            } else {
                System.out.println("Request failed with status code: " + response.getStatusCode());
                return "";
            }
        }

    public byte[] convertMultipartFileToByteArray(MultipartFile file) {
        try {
            return IOUtils.toByteArray(file.getInputStream());
        } catch (Exception e) {
            // Handle the exception appropriately
            e.printStackTrace();
            return null;
        }
    }
}
