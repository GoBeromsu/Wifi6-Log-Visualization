package com.jnu.wifi6.config;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfluxDBConfig {

  @Value("${influxdb.url}")
  private String influxDBUrl;

  @Value("${influxdb.username}")
  private String username;

  @Value("${influxdb.password}")
  private String password;

  @Value("${influxdb.bucket}")
  private String bucket;
  @Value("${influxdb.token}")
  private String token;

  @Bean
  public InfluxDBClient influxDBClient() {
    return InfluxDBClientFactory.create(influxDBUrl, token.toCharArray());
  }
}
