package com.example.demo.service;

public interface RateLimiter {

    Boolean tryConsume();
}
