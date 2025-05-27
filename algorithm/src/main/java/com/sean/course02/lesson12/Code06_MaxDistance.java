package com.sean.course02.lesson12;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-05-27 13:26
 * @Description 二叉树节点的最大距离
 */
public class Code06_MaxDistance {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int maxDistance1(Node head) {
        if (head == null) {
            return 0;
        }
        List<Node> arr = getPrelist(head);
        Map<Node, Node> map = getParentMap(head);
        int max = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i; j < arr.size(); j++) {
                max = Math.max(max, distance(map, arr.get(i), arr.get(j)));
            }
        }
        return max;
    }

    private static List<Node> getPrelist(Node head) {
        List<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        return arr;
    }

    private static void fillPrelist(Node head, List<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    private static Map<Node, Node> getParentMap(Node head) {
        Map<Node, Node> map = new HashMap<>();
        map.put(head, null);
        fillParentMap(head, map);
        return map;
    }

    private static void fillParentMap(Node head, Map<Node, Node> map) {
        if (head.left != null) {
            map.put(head.left, head);
            fillParentMap(head.left, map);
        }
        if (head.right != null) {
            map.put(head.right, head);
            fillParentMap(head.right, map);
        }
    }

    private static int distance(Map<Node, Node> map, Node o1, Node o2) {
        Set<Node> o1Set = new HashSet<>();
        Node cur = o1;
        o1Set.add(cur);
        while (map.get(cur) != null) {
            cur = map.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = map.get(cur);
        }
        Node lowestAncestor = cur;
        cur = o1;
        int distance1 = 1;
        while (cur != lowestAncestor) {
            cur = map.get(cur);
            distance1++;
        }
        cur = o2;
        int distance2 = 1;
        while (cur != lowestAncestor) {
            cur = map.get(cur);
            distance2++;
        }
        return distance1 + distance2 - 1;
    }

    public static int maxDistance2(Node head) {
        return process(head).maxDistance;
    }

    public static class Info {
        public int maxDistance;
        public int height;

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    private static Info process(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int p1 = leftInfo.maxDistance;
        int p2 = rightInfo.maxDistance;
        int p3 = leftInfo.height + rightInfo.height + 1;
        int maxDistance = Math.max(Math.max(p1, p2), p3);
        return new Info(maxDistance, height);
    }

    private static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    private static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (maxValue * Math.random()));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxDistance1(head) != maxDistance2(head)) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }

}
