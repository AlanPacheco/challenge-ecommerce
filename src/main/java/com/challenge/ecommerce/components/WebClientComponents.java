package com.challenge.ecommerce.components;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientComponents {

    @Bean
    public WebClient webClientCep(WebClient.Builder builder) {
        return builder
                .baseUrl("https://viacep.com.br/ws")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
