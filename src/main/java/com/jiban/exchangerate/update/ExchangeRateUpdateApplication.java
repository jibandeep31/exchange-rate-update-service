package com.jiban.exchangerate.update;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ExchangeRateUpdateApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExchangeRateUpdateApplication.class, args);
    }

}
