package com.jnu.wifi6.meraki.command;

import com.jnu.wifi6.influx.command.RestClient;
import com.jnu.wifi6.meraki.dto.GetMerakiDTO;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    GetMerakiDTO merakiData = getMerakiClient.execute(currentDate, 5000);

    // POST 요청 보내기
    restClient.sendPostRequest(flaskServerUrl, merakiData, Void.class);
    return merakiData;

  }

  private String getCurrentDateTime() {
    // 한국 시간대 설정
    ZoneId koreaZoneId = ZoneId.of("Asia/Seoul");
    // 오늘 날짜에 해당하는 자정 시간 설정
    LocalDateTime midnight = LocalDateTime.now(koreaZoneId).toLocalDate().atStartOfDay();
    return midnight.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "Z";
  }

}
