package com.jnu.wifi6.meraki.usecase;

import com.jnu.wifi6.influx.command.RestClient;
import com.jnu.wifi6.meraki.DTO.GetMerakiDTO;
import com.jnu.wifi6.meraki.command.GetMerakiClient;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduledGetMerakiInfo {

  private final GetMerakiClient getMerakiClient;
  private final RestClient restClient;

  @Scheduled(cron = "0 50 23 * * ?") // 매일 23:50에 작업 수행
  public void execute() {
    String currentDate = getCurrentDateTime();
    GetMerakiDTO merakiData = getMerakiClient.execute(currentDate, 1000000000, 300);

    String url = "https://external.api/endpoint";//todo 도커 컨테이너에 올라가는 외부 api 주소로 변경 할 것

    // POST 요청 보내기
    restClient.sendPostRequest(url, merakiData, Void.class); // Void.class는 응답 타입을 나타냅니다.

  }


  private String getCurrentDateTime() {
    LocalDateTime now = LocalDateTime.now();
    return now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "Z";
  }
}
