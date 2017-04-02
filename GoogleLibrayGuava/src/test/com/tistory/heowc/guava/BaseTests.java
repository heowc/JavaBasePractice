package com.tistory.heowc.guava;

import com.google.common.base.*;
import com.google.common.collect.Maps;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static com.google.common.base.Joiner.on;
import static com.google.common.primitives.UnsignedInts.parseUnsignedInt;

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

    @Test
    public void test_isNullOrEmpty() throws Exception {
        Assert.assertTrue(Strings.isNullOrEmpty(""));
        Assert.assertTrue(Strings.isNullOrEmpty(null));
    }

    @Test
    public void test_repeat() throws Exception {
        Assert.assertEquals("String is same content",
                Strings.repeat("wonchul ", 2),
                "wonchul wonchul ");
    }

    @Test
    public void test_converter() throws Exception {
        Assert.assertEquals(
                "String is '7b'",
                Converter.from(
                        Integer::toHexString,
                        s -> parseUnsignedInt(s, 16)).convert(123),
                "7b");
    }

    @Test
    public void test_joiner() throws Exception {
        Joiner joiner = Joiner.on(",").skipNulls();
        String joinNumber = joiner.join("1", null, "3", "4");

        Assert.assertEquals("1,3,4", joinNumber);
    }

    @Test
    public void test_splitter() throws Exception {
        Splitter splitter = Splitter.on(";");
        splitter.split("heo;won;chul").forEach(System.out::println);
    }

    @Test
    public void test_joinerMapJoiner() throws Exception {
        Joiner.MapJoiner mapJoiner = Joiner.on("&").withKeyValueSeparator("=");

        Map<String, String> map = Maps.newHashMap();
        map.put("key", "param");
        map.put("key2", "param2");

        Assert.assertEquals(mapJoiner.join(map), "key2=param2&key=param");
    }

    @Test
    public void test_splitterMapSplitter() throws Exception {
        Splitter.MapSplitter mapSplitter = Splitter.on("&").withKeyValueSeparator("=");

        Map<String, String> map = Maps.newHashMap();
        map.put("key", "param");
        map.put("key2", "param2");

        Map<String, String> map2 = mapSplitter.split("key2=param2&key=param");

        Assert.assertEquals(map.get("key"), map2.get("key"));
    }

    @After
    public void after_stop() {
        stopwatch.stop();
        System.out.println("Test Time : " + stopwatch);
    }
}
