package com.jnu.wifi6.influx.usecase;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InsertInfo {


  private final InfluxDBClient influxDBClient;
  private final String bucket;
  private final String org;

  public InsertInfo(InfluxDBClient influxDBClient, @Value("${influxdb.bucket}") String bucket,
      @Value("${influxdb.org}") String org) {
    this.influxDBClient = influxDBClient;
    this.bucket = bucket;
    this.org = org;
  }

  public void execute(final Long predictedValue) {
    try (WriteApi writeApi = influxDBClient.getWriteApi()) {
      Point point = Point.measurement("usage_prediction")
          .addTag("source", "model_v1")
          .addField("predicted_value", predictedValue)
          .time(Instant.now(), WritePrecision.NS);
      writeApi.writePoint(bucket, org, point);
    }
  }
}
