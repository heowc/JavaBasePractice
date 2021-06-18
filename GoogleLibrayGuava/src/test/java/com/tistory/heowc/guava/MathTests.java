package com.tistory.heowc.guava;

import com.google.common.math.BigIntegerMath;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MathTests {

	@Test
	void test_Log() throws Exception {
		System.out.println(LongMath.log2(2, RoundingMode.FLOOR));
		System.out.println(LongMath.log2(3, RoundingMode.UP));
	}

	@Test
	void test_checkedMultiply() throws Exception {
		try {
			IntMath.checkedMultiply(1 * 10000 * 10000, 100);
		} catch (ArithmeticException e) {
			e.printStackTrace();
		}

		assertTrue(true);
	}

	@Test
	void test_divide() throws Exception {
		long quotient = LongMath.divide(9, 2, RoundingMode.UP);

		assertEquals(quotient, 5L);
	}

	@Test
	void test_sqrt() throws Exception {
		BigInteger bigInteger = BigIntegerMath.sqrt(BigInteger.TEN.pow(4), RoundingMode.HALF_EVEN);

		assertEquals(bigInteger.intValue(), 100);
	}
}