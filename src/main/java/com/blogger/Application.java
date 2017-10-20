package com.blogger;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
public class Application {
  
  @PostConstruct
  void started() {
    TimeZone.setDefault(TimeZone.getTimeZone("IST"));
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
