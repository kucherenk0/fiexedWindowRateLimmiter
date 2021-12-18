package com.example.demo.config;

import com.example.demo.service.FixedWindowRateLimiter;
import com.example.demo.service.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public RateLimiter rateLimiter(){
        return new FixedWindowRateLimiter(3, 10000);
    }
}
