package com.icebear2n2.lotto.http;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class HttpClient {
    private final RestTemplate restTemplate;

    public String getData(String uri, HttpMethod httpMethod, HttpHeaders headers) {
        return restTemplate.exchange(
                uri,
                httpMethod,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<String>() {}
        ).getBody();
    }
}
