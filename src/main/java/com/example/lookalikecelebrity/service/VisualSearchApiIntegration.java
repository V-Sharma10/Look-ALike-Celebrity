package com.example.lookalikecelebrity.service;

import com.example.lookalikecelebrity.responses.ProductSearchResponse;
import com.meesho.feed.feedcommons.enums.FeedType;
import com.meesho.feedaggregatorclient.dtos.requests.products.ProductSearchRequest;
import com.meesho.storefront.clients.dtos.response.ProductFeedResponse;
import com.meesho.storefront.clients.dtos.views.product.ProductCatalogView;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class VisualSearchApiIntegration {

    public List<ProductSearchResponse> getVisualSearchResponse(String imageUrl) {
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
            return getTransformedData(response.getBody());
        }
        else {
            return null;
        }
    }

    private List<ProductSearchResponse> getTransformedData(ProductFeedResponse productFeedResponse) {
        List<ProductSearchResponse> responseList = new ArrayList<>();

        for(ProductCatalogView productCatalogView : productFeedResponse.getProductCatalogViews()) {
            responseList.add(ProductSearchResponse.builder()
                            .productId(Long.valueOf(productCatalogView.getProductSupplierView().getId()))
                            .productName(productCatalogView.getProductSupplierView().getName())
                            .productUrl(productCatalogView.getProductSupplierView().getImages().get(0))
                            .productPrice(Objects.nonNull(productCatalogView.getProductSupplierView().getMrpPrice())
                                    ? String.valueOf(productCatalogView.getProductSupplierView().getMrpPrice()): null)
                    .build());
        }
        return responseList;
    }
}
