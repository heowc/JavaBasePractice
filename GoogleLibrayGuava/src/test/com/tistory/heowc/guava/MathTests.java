package com.tistory.heowc.guava;

import com.google.common.math.BigIntegerMath;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.math.RoundingMode;

public class MathTests {

    @Test
    public void test_Log() throws Exception {
        System.out.println(LongMath.log2(2, RoundingMode.FLOOR));
        System.out.println(LongMath.log2(3, RoundingMode.UP));
    }

    @Test
    public void test_checkedMultiply() throws Exception {
        try {
            IntMath.checkedMultiply(1 * 10000 * 10000, 100);
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(true);
    }

    @Test
    public void test_divide() throws Exception {
        long quotient = LongMath.divide(9, 2, RoundingMode.UP);

        Assert.assertEquals(quotient, 5L);
    }

    @Test
    public void test_sqrt() throws Exception {
        BigInteger bigInteger = BigIntegerMath.sqrt(BigInteger.TEN.pow(4), RoundingMode.HALF_EVEN);

        Assert.assertEquals(bigInteger.intValue(), 100);
    }
}