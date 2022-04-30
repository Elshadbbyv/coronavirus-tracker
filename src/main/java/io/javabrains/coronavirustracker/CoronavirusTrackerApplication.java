package io.javabrains.coronavirustracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CoronavirusTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoronavirusTrackerApplication.class, args);
    }

}

