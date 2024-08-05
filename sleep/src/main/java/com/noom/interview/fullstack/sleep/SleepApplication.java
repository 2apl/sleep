package com.noom.interview.fullstack.sleep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SleepApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SleepApplication.class, args);
    }
}
