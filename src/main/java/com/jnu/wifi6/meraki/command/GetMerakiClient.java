package com.jnu.wifi6.meraki.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jnu.wifi6.config.MerakiConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GetMerakiClient {

  private final MerakiConfig merakiConfig;

  private static final String API_KEY = "X-Cisco-Meraki-API-Key";

  public void execute(String startDate) throws JsonProcessingException {
    String baseUrl = merakiConfig.getBaseUrl();

    HttpHeaders headers = getHeaders();
    RestTemplate restTemplate = new RestTemplate();

    List<Map<String, Object>> allData = new ArrayList<>();

    String url = String.format("%s/networks/%s/clients?perPage=1000&t0=%s", baseUrl,
        merakiConfig.getNetworkId(), startDate);

    while (url != null && !url.isEmpty()) {
      ResponseEntity<String> response = sendRequest(restTemplate, url, headers);
      if (response.getStatusCodeValue() != 200) {
        System.out.println("API 요청에 실패했습니다. 상태 코드: " + response.getStatusCode());
        System.out.println("응답 메시지: " + response.getBody());
        break;
      }

      ObjectMapper objectMapper = new ObjectMapper();
      List<Map<String, Object>> data = objectMapper.readValue(response.getBody(),
          new TypeReference<List<Map<String, Object>>>() {
          });
      String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
      System.out.println(prettyJson);
      allData.addAll(data);
    }
  }


  private HttpHeaders getHeaders() {
    HttpHeaders headers = new HttpHeaders();

    headers.set(API_KEY, merakiConfig.getApiKey());
    headers.setContentType(MediaType.APPLICATION_JSON);
    return headers;
  }

  private static String extractNextLink(List<String> linkHeader) {
    if (linkHeader != null) {
      for (String link : linkHeader) {
        if (link.contains("rel=\"next\"")) {
          return link.substring(link.indexOf('<') + 1, link.indexOf('>'));
        }
      }
    }
    return null;
  }


  public ResponseEntity<String> sendRequest(RestTemplate restTemplate, String url,
      HttpHeaders headers) {
    return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>("parameters", headers),
        String.class);
  }
}
