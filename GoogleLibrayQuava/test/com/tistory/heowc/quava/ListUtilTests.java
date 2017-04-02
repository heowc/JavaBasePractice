package com.tistory.heowc.quava;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

public class ListUtilTests {

    @Test
    public void test_newArrayList() {
        Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9).forEach(System.out::println);
    }

    @Test
    public void test_arrayAddItemToList() {
        Integer [] numberArray = {1, 2, 3, 5, 6, 7, 8, 9};
        Lists.asList(0, numberArray).forEach(System.out::println);
    }

    @Test
    public void test_partitionToLists() {
        Integer groupSize = 3;
        List<Integer> numberList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        Lists.partition(numberList, groupSize)
                .forEach(list -> {
                    list.forEach(System.out::print);
                    System.out.println();
                });
    }

    @Test
    public void test_cartesianProduct() {
        List<Integer> numberList1 = Lists.newArrayList(1, 2);
        List<Integer> numberList2 = Lists.newArrayList(3, 4);

        Lists.cartesianProduct(numberList1, numberList2)
                .forEach(System.out::println);
    }

    @Test
    public void test_reverse() {
        List<Integer> numberList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Lists.reverse(numberList).forEach(System.out::println);
    }

    @Test
    public void test_transform() {
        List<Integer> numberList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Lists.transform(numberList, value -> value * 2).forEach(System.out::println);
    }
}
