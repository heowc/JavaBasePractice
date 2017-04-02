package com.tistory.heowc.guava;

import com.google.common.base.Stopwatch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BaseTests {

    private Stopwatch stopwatch;

    @Before
    public void before_start() {
        stopwatch = Stopwatch.createStarted();
    }

    @Test
    public void test_stopwatch() throws Exception {
        Thread.sleep(1000L);
    }

    @After
    public void after_stop() {
        stopwatch.stop();
        System.out.println("Test Time : " + stopwatch);
    }
}
