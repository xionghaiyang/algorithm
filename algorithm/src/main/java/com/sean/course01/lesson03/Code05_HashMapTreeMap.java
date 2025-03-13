package com.sean.course01.lesson03;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * @Author xionghaiyang
 * @Date 2025-03-13 21:36
 * @Description 哈希表和有序表
 */
public class Code05_HashMapTreeMap {

    public static class Node {
        private int val;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    }

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("zhangsan", "我是张三");
        System.out.println(map.containsKey("zhangsan"));
        System.out.println(map.containsKey("zhang"));
        System.out.println(map.get("zhangsan"));

        map.put("zhangsan", "他是张三");
        System.out.println(map.get("zhangsan"));

        String test1 = "zhangsan";
        String test2 = "zhangsan";
        System.out.println(map.get(test1));
        System.out.println(map.get(test2));

        map.remove("zhangsan");
        System.out.println(map.containsKey("zhangsan"));
        System.out.println(map.get("zhangsan"));

        HashMap<Integer, String> map2 = new HashMap<>();
        map2.put(1234567, "我是1234567");
        Integer a = 1234567;
        Integer b = 1234567;
        System.out.println(a == b);
        System.out.println(map2.containsKey(a));
        System.out.println(map2.containsKey(b));

        Node node1 = new Node(1);
        Node node2 = new Node(1);
        HashMap<Node, String> map3 = new HashMap<>();
        map3.put(node1, "我进来了");
        System.out.println(map3.containsKey(node1));
        System.out.println(map3.containsKey(node2));

        System.out.println("=======================");
        TreeMap<Integer, String> treeMap1 = new TreeMap<>();
        treeMap1.put(3, "我是3");
        treeMap1.put(0, "我是0");
        treeMap1.put(7, "我是7");
        treeMap1.put(2, "我是2");
        treeMap1.put(5, "我是5");
        treeMap1.put(9, "我是9");

        System.out.println(treeMap1.containsKey(7));
        System.out.println(treeMap1.get(3));

        treeMap1.put(3, "他是3");
        System.out.println(treeMap1.get(3));

        treeMap1.remove(3);
        System.out.println(treeMap1.get(3));

        System.out.println(treeMap1.firstKey());
        System.out.println(treeMap1.lastKey());
        //<=5离5最近的key告诉我
        System.out.println(treeMap1.floorKey(5));
        //<=6离6最近的key告诉我
        System.out.println(treeMap1.floorKey(6));
        //>=5离最近的key告诉我
        System.out.println(treeMap1.ceilingKey(5));
        //>=6离6最近的key告诉我
        System.out.println(treeMap1.ceilingKey(6));

        Node node3 = new Node(3);
        Node node4 = new Node(4);
        TreeMap<Node, String> treeMap2 = new TreeMap<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.val - o2.val;
            }
        });
        treeMap2.put(node3, "我是node3");
        treeMap2.put(node4, "我是node4");
        System.out.println(treeMap2.firstKey());
    }

}
