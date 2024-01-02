package com.jnu.wifi6.meraki.usecase;

import com.jnu.wifi6.influx.command.RestClient;
import com.jnu.wifi6.influx.dto.PostInfluxDTO;
import com.jnu.wifi6.influx.dto.PredictionResponse;
import com.jnu.wifi6.influx.usecase.InsertInfo;
import com.jnu.wifi6.meraki.command.GetMerakiInfo;
import com.jnu.wifi6.meraki.dto.GetMerakiDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduledGetMerakiInfo {

  private final GetMerakiInfo getMerakiInfo;
  private final InsertInfo insertInfo;
  private final RestClient restClient;

  @Value("${flask.server.url}")
  private String flaskServerUrl;

  @Scheduled(cron = "0 50 23 * * ?") // 매일 23:50에 작업 수행
  public void execute() {
    GetMerakiDTO getMerakiDTO = getMerakiInfo.execute();

    PostInfluxDTO postInfluxDTO = processMerakiData(getMerakiDTO);
    insertInfo.execute(postInfluxDTO);

  }

  public PostInfluxDTO processMerakiData(final GetMerakiDTO merakiData) {
    ResponseEntity<PredictionResponse> response = restClient.sendPostRequest(flaskServerUrl,
        merakiData,
        PredictionResponse.class);

    long predictedUsage = 0;
    if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
      predictedUsage = response.getBody().predicted_usage();
    }
    return new PostInfluxDTO(predictedUsage);
  }

}
