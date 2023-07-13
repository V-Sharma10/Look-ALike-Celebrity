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
                        .searchId(null)
                        .isAutocorrectReverted(null)
                        .query(null)
                        .sortOption(null)
                        .selectedFilters(null)
                        .sessionState(null)
                        .build())
                .retryCount(3)
                .searchSessionId(null)
                .cursor(null)
                .limit(10)
                .build();

//        String request = "{\n" +
//                "    \"filter\": {\n" +
//                "        \"type\": \"visual_search\",\n" +
//                "        \"sort_option\": null,\n" +
//                "        \"selected_filters\": [],\n" +
//                "        \"session_state\": null,\n" +
//                "        \"selectedFilterIds\": [],\n" +
//                "        \"isClearFilterClicked\": false,\n" +
//                "        \"image_url\":"   + imageUrl +
//                "    },\n" +
//                "    \"search_session_id\": null,\n" +
//                "    \"cursor\": null,\n" +
//                "    \"offset\": 0,\n" +
//                "    \"limit\": 10,\n" +
//                "    \"supplier_id\": null,\n" +
//                "    \"featured_collection_type\": null,\n" +
//                "    \"meta\": null,\n" +
//                "    \"retry_count\": 3,\n" +
//                "    \"product_listing_page_id\": null\n" +
//                "}";

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
