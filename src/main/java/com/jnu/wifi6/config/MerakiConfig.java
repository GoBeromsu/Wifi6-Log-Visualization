package com.jnu.wifi6.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MerakiConfig {

  private final String baseUrl = "https://api.meraki.com/api/v1";

  @Value("${meraki.organizationId}")
  private String organizationId;

  @Value("${meraki.apiKey}")
  private String apiKey;

  @Value("${meraki.networkId}")
  private String networkId;

}
