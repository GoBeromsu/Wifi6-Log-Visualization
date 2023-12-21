package com.jnu.wifi6.meraki.usecase;

import com.jnu.wifi6.meraki.DTO.GetMerakiDTO;
import com.jnu.wifi6.meraki.command.GetMerakiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMerakiInfo {

  private final GetMerakiClient getMerakiClient;
  private final RestClient restClient;

  @Value("${flask.server.url}")
  private String flaskServerUrl;

  public GetMerakiDTO execute() {

    return getMerakiClient.execute(getDate("2023-12-13"), 1000000000, 300);
  }

  private String getDate(String date) {
    return date + "T00:00:00Z";
  }
}
