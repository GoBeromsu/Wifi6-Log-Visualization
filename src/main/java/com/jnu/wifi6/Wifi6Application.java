package com.jnu.wifi6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Wifi6Application {

  public static void main(String[] args) {
    SpringApplication.run(Wifi6Application.class, args);
  }

}
