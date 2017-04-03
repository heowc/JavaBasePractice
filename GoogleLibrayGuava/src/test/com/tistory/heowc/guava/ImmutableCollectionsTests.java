package com.tistory.heowc.guava;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import org.junit.Assert;
import org.junit.Test;

public class ImmutableCollectionsTests {

    private static final ImmutableCollection<Integer> IMMUTABLE_NUMBERS = ImmutableSet.of(1, 2, 3, 4, 5);

    @Test
    public void test_add() throws Exception {
        try {
            IMMUTABLE_NUMBERS.add(6);
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(true);
    }

    @Test
    public void test_remove() throws Exception {
        try {
            IMMUTABLE_NUMBERS.remove(1);
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(true);
    }

}
