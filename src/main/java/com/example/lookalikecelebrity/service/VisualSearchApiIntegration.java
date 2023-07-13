package com.example.lookalikecelebrity.service;

import com.meesho.feed.feedcommons.enums.FeedType;
import com.meesho.feedaggregatorclient.dtos.requests.products.ProductSearchRequest;
import com.meesho.storefront.clients.dtos.response.ProductFeedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VisualSearchApiIntegration {

    public ProductFeedResponse getVisualSearchResponse(String imageUrl) {
        ProductSearchRequest request = ProductSearchRequest.builder()
                .filter(ProductSearchRequest.Filter.builder()
                        .type(FeedType.VISUAL_SEARCH)
                        .imageUrl(imageUrl)
                        .build())
                .retryCount(3)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authentication", "b&67)j*nOP|g0<e");
        headers.set("APP-CLIENT-ID", "android");
        headers.set("MEESHO-USER-ID", "-1");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("MEESHO-ISO-COUNTRY-CODE", "IN");
        headers.set("MEESHO-CLIENT-ID", "android");
        headers.set("APP-VERSION-CODE", "500");
        headers.set("APP-USER-ID", "-1");

        HttpEntity<ProductSearchRequest> entity = new HttpEntity<>(request, headers);

        // Send POST request
        String url = "http://storefront-internal-search.meeshoint.in/api/1.0/search/products";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ProductFeedResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, ProductFeedResponse.class);

        if(response.getStatusCode().is2xxSuccessful()){
            return response.getBody();
        }
        else {
            return null;
        }
    }
}
