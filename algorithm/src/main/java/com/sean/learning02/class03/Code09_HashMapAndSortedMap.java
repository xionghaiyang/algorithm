package com.sean.learning02.class03;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class Code09_HashMapAndSortedMap {

    public static class Node {
        public int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        HashMap<Integer, String> test = new HashMap<>();
        Integer a = 190000;
        Integer b = 190000;
        System.out.println(a == b);

        test.put(a, "我是3");
        System.out.println(test.containsKey(b));

        HashMap<Node, String> test2 = new HashMap<>();
        Node node1 = new Node(1);
        Node node2 = new Node(1);
        test2.put(node1, "我是node1");
        System.out.println(test2.containsKey(node2));

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1000000, "我是1000000");
        map.put(2, "我是2");
        map.put(3, "我是3");
        map.put(4, "我是4");
        map.put(5, "我是5");
        map.put(6, "我是6");
        map.put(1000000, "我是10000001");
        System.out.println(map.containsKey(1));
        System.out.println(map.containsKey(10));

        System.out.println(map.get(4));
        System.out.println(map.get(10));

        map.put(4, "他是4");
        System.out.println(map.get(4));

        map.remove(4);
        System.out.println(map.get(4));
        System.out.println(map.get(1000000));

        HashSet<String> set = new HashSet<>();
        set.add("abc");
        set.contains("abc");
        set.remove("abc");
        //哈希表，增删改查在使用时O(1)
        System.out.println("========================");

        Integer c = 100000;
        Integer d = 100000;
        System.out.println(c.equals(d));

        Integer e = 127;
        Integer f = 127;
        System.out.println(e == f);

        HashMap<Node, String> map2 = new HashMap<>();
        Node node3 = new Node(1);
        Node node4 = node3;
        map2.put(node3, "我是node3");
        map2.put(node4, "我是node4");
        System.out.println(map2.size());

        System.out.println("=======================");

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
