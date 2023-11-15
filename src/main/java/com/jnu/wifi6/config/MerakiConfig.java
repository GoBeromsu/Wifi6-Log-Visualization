package com.jnu.wifi6.config;

import org.springframework.beans.factory.annotation.Value;


public class MerakiConfig {

  @Value("${meraki.organizationId}")
  private String organizationId;

  @Value("{meraki.apiKey}")
  private String apiKey;

  @Value("{meraki.networkId}")
  private String networkId;

}
