package com.tistory.heowc.guava;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.partitioningBy;

public class ListUtilTests {

    @Test
    public void test_newArrayList() {
        Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9).forEach(System.out::println);

        // JDK 6 이하
        List<Integer> list1 = new ArrayList<Integer>();

        // JDK 7 이상
        List<Integer> list2 = new ArrayList<>();

        // Guava
        List<Integer> list3 = Lists.newArrayList();
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

        // JDK 8
        Map<Boolean, List<Integer>> groupMap = numberList.stream()
                .collect(partitioningBy(i -> i > 5));
        Lists.newArrayList(groupMap.values())
            .forEach(list -> {
                list.forEach(System.out::print);
                System.out.println();
            });
        // 1234
        // 56789

        // Guava
        Lists.partition(numberList, groupSize)
            .forEach(list -> {
                list.forEach(System.out::print);
                System.out.println();
            });
        // 123
        // 456
        // 789
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
