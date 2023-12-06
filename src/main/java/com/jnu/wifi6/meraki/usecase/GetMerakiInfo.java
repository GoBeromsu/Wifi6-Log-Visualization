package com.jnu.wifi6.meraki.usecase;

import com.jnu.wifi6.meraki.command.GetMerakiClient;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetMerakiInfo {

  private final GetMerakiClient getMerakiClient;

  public Mono<List<Map<String, Object>>> execute() {

    Long totalCleints = 0L;
    Long totalUsage = 0L;

    return getMerakiClient.execute(getDate("2023-12-06"), 1000000000, 300);
//    return new GetMerakiInfoDTO(totalCleints, totalUsage);
  }

  private String getDate(String date) {
    return date + "T00:00:00Z";
  }
}
