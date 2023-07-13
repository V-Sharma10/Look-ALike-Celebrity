package com.example.lookalikecelebrity.httpHandlers;

import com.example.lookalikecelebrity.DTO.CelebrityDetailsDto;
import com.example.lookalikecelebrity.Dao.CelebrityDetailsDao;
import com.example.lookalikecelebrity.requests.AzurePredictionRequest;
import com.example.lookalikecelebrity.responses.AzurePredictionResponse;
//import com.example.lookalikecelebrity.responses.CelebLookAlikeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AzurePredictionHandler {

    private static final String AZURE_PREDICTION_SERVICE_URL = "https://tthcustomv-prediction.cognitiveservices.azure.com/customvision/v3.0/Prediction/a8d14ac0-dd0d-4126-8cdd-841f2d93dfe3/classify/iterations/Iteration3/image";
    private static final String PREDICTION_KEY = "Prediction-Key";

    @Autowired
    private CelebrityDetailsDao celebrityDetailsDao;
    private final RestTemplate restTemplate = new RestTemplate();

    public List<CelebrityDetailsDto> getCelebLookAlikeResponse(InputStream inputStream) throws Exception {

        AzurePredictionResponse azurePredictionResponse = getPredictionsForImage(inputStream);
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

    private AzurePredictionResponse getPredictionsForImage(InputStream inputStream) throws Exception {

        String url = AZURE_PREDICTION_SERVICE_URL;
        HttpHeaders httpHeaders = createHttpHeaders();
        AzurePredictionRequest azurePredictionRequest = createAzureRequest(inputStream);
//        HttpEntity requestEntity = new InputStreamEntity(inputStream, ContentType.APPLICATION_OCTET_STREAM);
        HttpEntity<AzurePredictionRequest> requestEntity = new HttpEntity<>(azurePredictionRequest,httpHeaders);
        return makeRequest(url,requestEntity);

    }

    private AzurePredictionRequest createAzureRequest(InputStream inputStream) {
        return AzurePredictionRequest.builder()
                .inputStream(inputStream)
                .build();
    }

    @Retryable(value = Exception.class, backoff = @Backoff(delay = 10000))
    private AzurePredictionResponse makeRequest(String url , HttpEntity requestEntity) throws Exception {
        try{
            ResponseEntity<AzurePredictionResponse> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, AzurePredictionResponse.class);
            log.info("Response Code {}", response.getStatusCodeValue());
            log.info("Response Data {}", response.getBody());
            return response.getBody();
        } catch (Exception e) {
            log.error("QwestApi Failed", e);
            throw new Exception("Error occurred while making Qwest API request");
        }
    }

    private HttpHeaders createHttpHeaders() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(PREDICTION_KEY, "5af8ab79cc5c4a7fa4d66e22582e0479");
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
        return httpHeaders;

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

}
