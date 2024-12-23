package com.emr.emr.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/s1/health-check")
public class EmrController {

    private Random random = new Random();

    @GetMapping
    public String getHello() {
        System.out.println("==========new Processing request at: " + System.currentTimeMillis());

        if (random.nextBoolean()) {
            System.out.println("Simulating failure...");
            throw new RuntimeException("Simulated failure, triggering retry and circuit breaker");
        }

        System.out.println("Sucess>>>>>>>>>>>>" + System.currentTimeMillis());
        return "Hello from EMR service";
    }
}
