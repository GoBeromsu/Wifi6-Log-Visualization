package com.jnu.wifi6.meraki;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jnu.wifi6.meraki.usecase.GetMerakiInfo;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class MerakiController {

  private final GetMerakiInfo getMerakiInfo;

  @GetMapping("/meraki")
  public Mono<List<Map<String, Object>>>  getMerakiInfo() throws JsonProcessingException {
    return getMerakiInfo.execute();
  }

}
