package com.tistory.heowc.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class RateLimiterTest {

    private static final Logger logger = LoggerFactory.getLogger(RateLimiterTest.class);

    @Test
    public void test() throws InterruptedException {
        RateLimiter rateLimiter = RateLimiter.create(5.0);

        AtomicInteger count = new AtomicInteger();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (rateLimiter.tryAcquire()) {
                    logger.info("acquire");
                    count.incrementAndGet();
                } else {
                    logger.error("non acquire");
                }
            }

        }, 0, 100);

        Thread.sleep(1000L);

        assertThat(count.get(), is(5));
    }
}
