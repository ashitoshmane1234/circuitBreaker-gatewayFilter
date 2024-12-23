package com.patient.patient.patient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/s4/health-check")
public class PatientController {
    
    @GetMapping
    public String getHello() {
        return "Hello from Patient service";
    }
}
