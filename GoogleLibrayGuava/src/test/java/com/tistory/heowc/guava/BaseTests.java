package com.tistory.heowc.guava;

import com.google.common.base.Converter;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.google.common.primitives.UnsignedInts.parseUnsignedInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BaseTests {

	private Stopwatch stopwatch;

	@BeforeEach
	void before_start() {
		stopwatch = Stopwatch.createStarted();
	}

	@Test
	void test_stopwatch() throws Exception {
		Thread.sleep(1000L);
	}

	@Test
	void test_isNullOrEmpty() throws Exception {
		assertTrue(Strings.isNullOrEmpty(""));
		assertTrue(Strings.isNullOrEmpty(null));
	}

	@Test
	void test_repeat() throws Exception {
		assertEquals("String is same content",
				Strings.repeat("wonchul ", 2),
				"wonchul wonchul ");
	}

	@Test
	void test_converter() throws Exception {
		assertEquals(
				"String is '7b'",
				Converter.from(
						Integer::toHexString,
						s -> parseUnsignedInt(s, 16)).convert(123),
				"7b");
	}

	@Test
	void test_joiner() throws Exception {
		Joiner joiner = Joiner.on(",").skipNulls();
		String joinNumber = joiner.join("1", null, "3", "4");

		assertEquals("1,3,4", joinNumber);
	}

	@Test
	void test_splitter() throws Exception {
		Splitter splitter = Splitter.on(";");
		splitter.split("heo;won;chul").forEach(System.out::println);
	}

	@Test
	void test_joinerMapJoiner() throws Exception {
		Joiner.MapJoiner mapJoiner = Joiner.on("&").withKeyValueSeparator("=");

		Map<String, String> map = Maps.newHashMap();
		map.put("key", "param");
		map.put("key2", "param2");

		assertEquals(mapJoiner.join(map), "key2=param2&key=param");
	}

	@Test
	void test_splitterMapSplitter() throws Exception {
		Splitter.MapSplitter mapSplitter = Splitter.on("&").withKeyValueSeparator("=");

		Map<String, String> map = Maps.newHashMap();
		map.put("key", "param");
		map.put("key2", "param2");

		Map<String, String> map2 = mapSplitter.split("key2=param2&key=param");

		assertEquals(map.get("key"), map2.get("key"));
	}

	@AfterEach
	void after_stop() {
		stopwatch.stop();
		System.out.println("Test Time : " + stopwatch);
	}
}
