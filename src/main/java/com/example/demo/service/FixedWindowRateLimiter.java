package com.example.demo.service;

import java.util.concurrent.atomic.AtomicInteger;


public class FixedWindowRateLimiter implements RateLimiter {
    private final int limit;
    private final long period;
    private long startTime = 0;
    private final AtomicInteger requestsNumber = new AtomicInteger();


    public FixedWindowRateLimiter(int limit, long period) {
        this.limit = limit;
        this.period = period;
    }

    @Override
    public synchronized Boolean tryConsume() {

        if(startTime == 0){
            startTime =  System.currentTimeMillis();
        }

        if(requestsNumber.getAndIncrement() > limit - 1) {
            if (System.currentTimeMillis() - startTime > period) {
                startTime = System.currentTimeMillis();
                requestsNumber.set(1);
            } else {
                return false;
            }
        }
        return true;
    }

}
