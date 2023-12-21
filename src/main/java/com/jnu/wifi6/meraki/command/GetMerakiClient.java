package com.jnu.wifi6.meraki.command;

import com.jnu.wifi6.config.MerakiConfig;
import com.jnu.wifi6.meraki.Mapper;
import com.jnu.wifi6.meraki.dto.ClientDeviceDTO;
import com.jnu.wifi6.meraki.dto.GetMerakiDTO;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class GetMerakiClient {

  private final MerakiConfig merakiConfig;
  private static final String API_KEY = "X-Cisco-Meraki-API-Key";

  public GetMerakiDTO execute(String startDate, int pageNumber, int perPage) {
    WebClient webClient = WebClient.builder().baseUrl(merakiConfig.getBaseUrl()).build();
    String url = String.format("/networks/%s/clients?perPage=%d&page=%d&t0=%s",
        merakiConfig.getNetworkId(), perPage, pageNumber, startDate);

    List<ClientDeviceDTO> responseMono = webClient.get().uri(url)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .header(API_KEY, merakiConfig.getApiKey()).retrieve()
        .bodyToMono(new ParameterizedTypeReference<List<ClientDeviceDTO>>() {
        }).block();

    List<ClientDeviceDTO> clientDeviceList = Optional.ofNullable(responseMono)
        .orElse(Collections.emptyList());

    return Mapper.mapToDTO(clientDeviceList);
  }
}
