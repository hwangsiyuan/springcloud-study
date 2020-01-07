package com.hussein;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableTurbineStream
@EnableHystrixDashboard
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
