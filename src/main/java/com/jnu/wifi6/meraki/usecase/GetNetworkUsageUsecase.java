package com.jnu.wifi6.meraki.usecase;

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
public class GetNetworkUsageUsecase {

  private final MerakiConfig merakiConfig;

  private static final String API_KEY = "X-Cisco-Meraki-API-Key";

  public List<Map<String, Object>> execute() throws JsonProcessingException {
    String baseUrl = merakiConfig.getBaseUrl();
    HttpHeaders headers = new HttpHeaders();

    headers.set(API_KEY, merakiConfig.getApiKey());
    headers.setContentType(MediaType.APPLICATION_JSON);
    String startDate = "2023-10-25T00:00:00Z";

    RestTemplate restTemplate = new RestTemplate();
    List<Map<String, Object>> allData = new ArrayList<>();

    String url = String.format("%s/networks/%s/clients?perPage=1000&t0=%s", baseUrl,
        merakiConfig.getNetworkId(), startDate);
    while (url != null && !url.isEmpty()) {
      HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
      ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity,
          String.class);

      if (response.getStatusCodeValue() != 200) {
        System.out.println("API 요청에 실패했습니다. 상태 코드: " + response.getStatusCode());
        System.out.println("응답 메시지: " + response.getBody());
        break;
      }

      ObjectMapper objectMapper = new ObjectMapper();
      List<Map<String, Object>> data = objectMapper.readValue(response.getBody(),
          new TypeReference<List<Map<String, Object>>>() {
          });
      allData.addAll(data);

      url = extractNextLink(response.getHeaders().get("Link"));
    }

    return allData;
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
}
