package com.challenge.ecommerce.services;

import com.challenge.ecommerce.dto.ZipCodeDTO;
import com.challenge.ecommerce.services.exceptions.ResourceNotFoundException;
import com.challenge.ecommerce.services.exceptions.ZipCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class ZipCodeService {

    @Autowired
    private WebClient webClientCep;

    public ZipCodeDTO findByZipCode(String zipCode) {
        try {
            Mono<ZipCodeDTO> monoZipCode = webClientCep
                    .method(HttpMethod.GET)
                    .uri("/{zipCode}/json", zipCode)
                    .retrieve()
                    .bodyToMono(ZipCodeDTO.class);

            ZipCodeDTO zipCodeDTO = monoZipCode.block();
            return zipCodeDTO;
        } catch (WebClientResponseException err) {
            throw new ZipCodeException("Zip code invalid: " + zipCode);
        }
    }
}
