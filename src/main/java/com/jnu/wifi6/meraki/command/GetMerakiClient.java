package com.jnu.wifi6.meraki.command;

import com.jnu.wifi6.config.MerakiConfig;
import com.jnu.wifi6.meraki.DTO.ClientDeviceDTO;
import com.jnu.wifi6.meraki.DTO.GetMerakiDTO;
import com.jnu.wifi6.meraki.Mapper;
import java.util.List;
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

  public Mono<GetMerakiDTO> execute(String startDate, int pageNumber, int perPage) {
    WebClient webClient = WebClient.builder().baseUrl(merakiConfig.getBaseUrl()).build();
    String url = String.format("/networks/%s/clients?perPage=%d&page=%d&t0=%s",
        merakiConfig.getNetworkId(), perPage, pageNumber, startDate);

    return webClient.get().uri(url)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .header(API_KEY, merakiConfig.getApiKey()).retrieve()
        .bodyToMono(new ParameterizedTypeReference<List<ClientDeviceDTO>>() {
        }).flatMap(list -> Mapper.mapToDTO(Mono.just(list))); // 데이터 변환
  }
}

