package com.jnu.wifi6.meraki;

import com.jnu.wifi6.meraki.DTO.GetMerakiDTO;
import com.jnu.wifi6.meraki.usecase.GetMerakiInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class MerakiController {

  private final GetMerakiInfo getMerakiInfo;

  @GetMapping("/meraki")
  public Mono<GetMerakiDTO> getMerakiInfo() {
    return getMerakiInfo.execute();
  }

}
