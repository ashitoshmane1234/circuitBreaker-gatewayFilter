package com.mindbowser.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @RequestMapping("/{serviceName}")
    public ResponseEntity<String> serviceFallback(@PathVariable("serviceName") String serviceName) {
        String errorMessage = String.format("%s service is currently unavailable. Please try again later.",
                serviceName.toUpperCase());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(errorMessage);
    }
}