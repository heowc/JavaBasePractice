package com.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Application {

    public static void main(String[] args) {
        System.out.println(foo(null));
        // error: [NullAway] passing @Nullable parameter 'null' where @NonNull is required

        Map<String, String> map = new HashMap<>();
        map.put("a", "b");
        System.out.println(map.get("c").toLowerCase());
        // error: [NullAway] dereferenced expression map.get("c") is @Nullable

        Set<Short> s = new HashSet<>();
        for (short i = 0; i < 100; i++) {
            s.add(i);
            s.remove(i - 1);
        }
        System.out.println(s.size());
        // error: [CollectionIncompatibleType] Argument 'i - 1' should not be passed to this method; its type int is not compatible with its collection's type argument Short
    }

    private static String foo(String bar) {
        return bar.toLowerCase();
    }
}
