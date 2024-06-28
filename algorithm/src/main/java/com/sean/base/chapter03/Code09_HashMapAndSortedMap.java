package com.sean.base.chapter03;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2022-08-28 19:54
 * @Description 哈希表与有序表
 */
public class Code09_HashMapAndSortedMap {

    public static class Node {
        public int value;

        public Node(int value) {
            this.value = value;
        }

    }

    public static void main(String[] args) {
        //哈希表，增、删、改、差，在使用时，O(1)
        Map<Integer, String> test1 = new HashMap<>();
        Integer a = 10000;
        Integer b = 10000;
        System.out.println(a == b);
        test1.put(a, "我是a");
        System.out.println(test1.containsKey(b));
        System.out.println(test1.get(a));

        HashMap<Node, String> test2 = new HashMap<>();
        Node node1 = new Node(1);
        Node node2 = new Node(1);
        test2.put(node1, "我是node1");
        System.out.println(test2.containsKey(node2));

        Set<String> set = new HashSet<>();
        set.add("abc");
        System.out.println(set.contains("abc"));
        set.remove("abc");

        //有序表 O(logN)
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "我是3");
        treeMap.put(4, "我是4");
        treeMap.put(8, "我是8");
        treeMap.put(5, "我是5");
        treeMap.put(7, "我是7");
        treeMap.put(1, "我是1");
        treeMap.put(2, "我是2");

        System.out.println(treeMap.containsKey(1));
        System.out.println(treeMap.containsKey(10));

        System.out.println(treeMap.get(4));
        System.out.println(treeMap.get(10));

        treeMap.put(4, "他是4");
        System.out.println(treeMap.get(4));

        treeMap.remove(4);
        System.out.println(treeMap.get(4));

        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.lastKey());
        //<=4
        System.out.println(treeMap.floorKey(4));
        //>=4
        System.out.println(treeMap.ceilingKey(4));
    }

}
