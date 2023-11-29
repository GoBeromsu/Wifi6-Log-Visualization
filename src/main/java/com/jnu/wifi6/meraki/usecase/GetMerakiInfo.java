package com.jnu.wifi6.meraki.usecase;

import com.jnu.wifi6.meraki.DTO.GetMerakiInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMerakiInfo {

  public GetMerakiInfoDTO execute() {
    return new GetMerakiInfoDTO();
  }
}
