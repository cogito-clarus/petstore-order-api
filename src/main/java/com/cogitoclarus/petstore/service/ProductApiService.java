package com.cogitoclarus.petstore.service;

import com.cogitoclarus.petstore.config.ProductApiConfig;
import com.cogitoclarus.petstore.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class ProductApiService {

    @Autowired
    private ProductApiConfig productApiConfig;

    private RestTemplate restTemplate;

    private HttpEntity<String> httpEntity;

    public ProductApiService() {
        HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.add("Accept-Encoding", "application/json");
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpEntity = new HttpEntity<String>(httpHeaders);
        restTemplate = new RestTemplate();
    }

    public ProductDTO getProductById(String id) {
        ResponseEntity<ProductDTO> responseEntity =
                restTemplate.exchange(productApiConfig.getBaseUrl()+"/{id}", HttpMethod.GET, httpEntity, ProductDTO.class, id);
        log.debug("product API response: {}", responseEntity == null ? "null" : responseEntity.toString());
        return responseEntity.getBody();
    }

    public List<ProductDTO> getAllProducts() {
        ResponseEntity<List<ProductDTO>> responseEntity =
                restTemplate.exchange(productApiConfig.getBaseUrl(), HttpMethod.GET, httpEntity,
                        new ParameterizedTypeReference<List<ProductDTO>>(){});
        log.debug("product API response: {}", responseEntity == null ? "null" : responseEntity.toString());
        return responseEntity.getBody();
    }
}
