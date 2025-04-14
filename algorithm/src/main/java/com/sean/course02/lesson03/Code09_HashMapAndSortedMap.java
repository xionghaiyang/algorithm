package com.sean.course02.lesson03;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-04-14 13:53
 * @Description 哈希表和有序表
 */
public class Code09_HashMapAndSortedMap {

    public static class Node {
        public int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        //哈希表，增、删、改、查，在使用时，O(1)
        Map<Integer, String> map = new HashMap<>();
        Integer a = 19000000;
        Integer b = 19000000;
        System.out.println(a == b);
        map.put(a, "我是3");
        System.out.println(map.containsKey(b));
        map.put(1, "我是1");
        map.put(2, "我是2");
        System.out.println(map.containsKey(1));
        System.out.println(map.containsKey(2));
        System.out.println(map.get(1));
        System.out.println(map.get(2));
        map.put(1, "他是1");
        System.out.println(map.get(1));
        System.out.println(map.size());
        map.remove(2);
        System.out.println(map.containsKey(2));
        System.out.println(map.get(2));
        System.out.println(map.size());

        System.out.println("==========");
        Set<String> set = new HashSet<>();
        set.add("abc");
        System.out.println(set.contains("abc"));
        set.remove("abc");
        System.out.println(set.contains("abc"));

        System.out.println("=================");
        //有序表 O(logN)
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "我是3");
        treeMap.put(4, "我是4");
        treeMap.put(5, "我是5");
        treeMap.put(6, "我是6");
        treeMap.put(7, "我是7");
        System.out.println(treeMap.containsKey(3));
        System.out.println(treeMap.containsKey(4));
        System.out.println(treeMap.get(3));
        System.out.println(treeMap.get(4));

        treeMap.put(3, "他是3");
        System.out.println(treeMap.get(3));
        treeMap.remove(4);
        System.out.println(treeMap.containsKey(4));
        System.out.println(treeMap.get(4));

        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.lastKey());
        //<=4
        System.out.println(treeMap.floorKey(4));
        //>=4
        System.out.println(treeMap.ceilingKey(4));
    }

}
