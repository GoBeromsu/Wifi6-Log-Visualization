package com.jnu.wifi6.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfluxDBConfig {

  @Value("${influxdb.url}")
  private String serverURL;

  @Value("{influxdb.username}")
  private String username;

  @Value("{influxdb.password}")
  private String password;

  @Value("{influxdb.database}")
  private String database;

  @Value("{influxdb.retentionPolicy}")
  private String retentionPolicy;
}
