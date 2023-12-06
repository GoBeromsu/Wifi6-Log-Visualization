package com.jnu.wifi6.meraki.command;

import com.jnu.wifi6.config.MerakiConfig;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetMerakiClient {

  private final MerakiConfig merakiConfig;
  private static final String API_KEY = "X-Cisco-Meraki-API-Key";


  public Mono<List<Map<String, Object>>> execute(String startDate, int pageNumber, int perPage) {
    WebClient webClient = WebClient.builder()
        .baseUrl(merakiConfig.getBaseUrl())
        .build();
    String url = String.format("/networks/%s/clients?perPage=%d&page=%d&t0=%s",
        merakiConfig.getNetworkId(), perPage, pageNumber, startDate);

    return webClient.get()
        .uri(url)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .header(API_KEY, merakiConfig.getApiKey())
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {
        })
        .doOnError(error -> {
          System.out.println("error = " + error);
        });
  }

}
