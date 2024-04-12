package com.czetsuyatech.vertical.events.client;

import com.czetsuyatech.vertical.events.EnableUnifiedKafkaVerticalEvents;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableUnifiedKafkaVerticalEvents
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
