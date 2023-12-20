package com.jnu.wifi6.influx.command;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {

  public <T, R> ResponseEntity<R> sendPostRequest(String url, T requestBody,
      Class<R> responseType) {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<T> entity = new HttpEntity<>(requestBody, headers);

    return restTemplate.postForEntity(url, entity, responseType);
  }
}