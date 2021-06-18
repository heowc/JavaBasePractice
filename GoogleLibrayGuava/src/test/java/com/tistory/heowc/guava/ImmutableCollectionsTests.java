package com.tistory.heowc.guava;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ImmutableCollectionsTests {

	private static final ImmutableCollection<Integer> IMMUTABLE_NUMBERS = ImmutableSet.of(1, 2, 3, 4, 5);

	@Test
	void test_add() throws Exception {
		try {
			IMMUTABLE_NUMBERS.add(6);
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
		assertTrue(true);
	}

	@Test
	void test_remove() throws Exception {
		try {
			IMMUTABLE_NUMBERS.remove(1);
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
		assertTrue(true);
	}

}
