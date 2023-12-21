package com.jnu.wifi6.meraki.usecase;

import com.jnu.wifi6.meraki.command.GetMerakiInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduledGetMerakiInfo {

  private final GetMerakiInfo getMerakiInfo;

  @Scheduled(cron = "0 50 23 * * ?") // 매일 23:50에 작업 수행
  public void execute() {
    getMerakiInfo.execute();
  }

}
