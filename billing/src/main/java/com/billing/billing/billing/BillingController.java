package com.billing.billing.billing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/s3/health-check")
public class BillingController {

    @GetMapping
    public String getHello() {
        return "Hello from Billing service";
    }

}
