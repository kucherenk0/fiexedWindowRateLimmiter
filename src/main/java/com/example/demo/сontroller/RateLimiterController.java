package com.example.demo.сontroller;

import com.example.demo.service.RateLimiter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RateLimiterController {

    private final RateLimiter rateLimiter;

    public RateLimiterController(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @GetMapping
    public ResponseEntity<String> get() {
        if(rateLimiter.tryConsume()){
            return ResponseEntity.ok("");
        }
        return ResponseEntity.status(429).body("Too many requests");
    }

}
