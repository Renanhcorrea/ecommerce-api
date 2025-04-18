package com.ecommerce.service;

import com.ecommerce.dto.ViaCepResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    private final RestTemplate restTemplate;

    @Value("${viacep.url}")
    private String viaCepBaseUrl;

    public ViaCepService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ViaCepResponseDTO fetchAddressByCep(String cep){
        String url = viaCepBaseUrl + cep + "/json/";
        return restTemplate.getForObject(url, ViaCepResponseDTO.class);
    }
}
