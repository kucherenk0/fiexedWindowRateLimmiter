package com.example.demo.service;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FixedWindowRateLimiterTest {


    @Test
    public void tryConsumeSuccess() {
        int requestsLimit = 5;
        long ratePeriod = 10 * 1000;

        RateLimiter rateLimiter = new FixedWindowRateLimiter(requestsLimit, ratePeriod);

        Boolean[] isConsumed = new Boolean[10];

        for (int i = 0; i < 10; ++i) {
            isConsumed[i] = rateLimiter.tryConsume();
        }

        for (int i = 0; i < 10; ++i) {
            if (i < 5) {
                assertTrue(isConsumed[i]);
            } else {
                assertFalse(isConsumed[i]);
            }
        }
    }

    @Test
    public void tryConsumeAfterTimeout() throws InterruptedException {
        int requestsLimit = 5;
        long ratePeriod = 3 * 1000;

        RateLimiter rateLimiter = new FixedWindowRateLimiter(requestsLimit, ratePeriod);

        Boolean[] isConsumed = new Boolean[20];

        for (int i = 0; i < 10; ++i) {
            isConsumed[i] = rateLimiter.tryConsume();
        }

        TimeUnit.SECONDS.sleep(3);

        for (int i = 10; i < 20; ++i) {
            isConsumed[i] = rateLimiter.tryConsume();
        }

        Arrays.stream(isConsumed).forEach(
                System.out::println
        );

        for (int i = 0; i < 20; ++i) {
            if (i < 5 || (i > 9 && i < 15)) {
                assertTrue(isConsumed[i]);
            } else {
                assertFalse(isConsumed[i]);
            }
        }
    }
}