package com.sean.nowcoder.HJ8;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<Integer, Integer> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int index = sc.nextInt();
            int value = sc.nextInt();
            map.put(index, map.getOrDefault(index, 0) + value);
        }
        Set<Integer> keys = map.keySet();
        for (int key : keys) {
            int value = map.get(key);
            System.out.println(key + " " + value);
        }
    }

}
