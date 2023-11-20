package com.jnu.wifi6.meraki;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jnu.wifi6.meraki.DTO.GetNetworkUsage;
import com.jnu.wifi6.meraki.usecase.GetClientCountUsecase;
import com.jnu.wifi6.meraki.usecase.GetNetworkUsageUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MerakiController {

  private final GetClientCountUsecase getClientCountUsecase;
  private final GetNetworkUsageUsecase getNetworkUsageUsecase;

  @GetMapping("/meraki/usage")
  public void getNetworkUsage() {
    getClientCountUsecase.execute();
  }

  @GetMapping("/meraki/client")
  public GetNetworkUsage getClient() throws JsonProcessingException {
    return getNetworkUsageUsecase.execute();
  }
}
