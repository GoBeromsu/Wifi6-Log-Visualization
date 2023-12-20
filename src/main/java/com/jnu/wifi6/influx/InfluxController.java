package com.jnu.wifi6.influx;

import com.jnu.wifi6.influx.usecase.InsertInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/influx")
public class InfluxController {

  private final InsertInfo insertInfo;


  @PostMapping("/")
  public void insertInfo(Long predictedValue) {
    insertInfo.execute(predictedValue);
  }
}
