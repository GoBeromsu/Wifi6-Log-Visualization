package com.jnu.wifi6.meraki;

import com.jnu.wifi6.meraki.DTO.GetMerakiInfoDTO;
import com.jnu.wifi6.meraki.usecase.GetMerakiInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MerakiController {

  private final GetMerakiInfo getMerakiInfo;

  @GetMapping("/meraki")
  public GetMerakiInfoDTO getMerakiInfo() {
    return new GetMerakiInfo().execute();
  }

}
