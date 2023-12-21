package com.jnu.wifi6.meraki.command;

import com.jnu.wifi6.influx.command.RestClient;
import com.jnu.wifi6.meraki.dto.GetMerakiDTO;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
    String currentDate = getCurrentDateTime();
    GetMerakiDTO merakiData = getMerakiClient.execute(currentDate, 1000000000, 300);

    // POST 요청 보내기
    restClient.sendPostRequest(flaskServerUrl, merakiData, Void.class); // Void.class는 응답 타입을 나타냅니다.
    return merakiData;

  }

  private String getCurrentDateTime() {
    LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
    return now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "Z";
  }
}
