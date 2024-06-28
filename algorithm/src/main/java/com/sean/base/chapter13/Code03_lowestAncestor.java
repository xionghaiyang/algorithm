package com.sean.base.chapter13;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-28 20:37
 * @Description: TODO
 */
public class Code03_lowestAncestor {

    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public Node lowestAncestor1(Node head, Node node1, Node node2) {
        if (head == null) {
            return null;
        }
        //key的父节点是value
        Map<Node, Node> parentMap = new HashMap<>();
        parentMap.put(head, null);
        fillParentMap(head, parentMap);
        Set<Node> set = new HashSet<>();
        Node cur = node1;
        set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            set.add(cur);
        }
        cur = node2;
        while (!set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    private void fillParentMap(Node head, Map<Node, Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }

    public Node lowestAncestor2(Node head, Node a, Node b) {
        return process(head, a, b).res;
    }

    public class Info {
        public boolean findA;
        public boolean findB;
        public Node res;

        public Info(boolean findA, boolean findB, Node res) {
            this.findA = findA;
            this.findB = findB;
            this.res = res;
        }
    }

    private Info process(Node x, Node a, Node b) {
        if (x == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(x.left, a, b);
        Info rightInfo = process(x.right, a, b);
        boolean findA = (x == a) || leftInfo.findA || rightInfo.findA;
        boolean findB = (x == b) || leftInfo.findB || rightInfo.findB;
        Node res = null;
        if (leftInfo.res != null) {
            res = leftInfo.res;
        } else if (rightInfo.res != null) {
            res = rightInfo.res;
        } else {
            if (findA && findB) {
                res = x;
            }
        }
        return new Info(findA, findB, res);
    }

    public Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    private Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        List<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    private void fillPrelist(Node head, List<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        boolean succeed = true;
        Code03_lowestAncestor solution = new Code03_lowestAncestor();
        System.out.println("test begin!");
        for (int i = 0; i < testTimes; i++) {
            Node head = solution.generateRandomBST(maxLevel, maxValue);
            Node node1 = solution.pickRandomOne(head);
            Node node2 = solution.pickRandomOne(head);
            if (solution.lowestAncestor1(head, node1, node2) != solution.lowestAncestor2(head, node1, node2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
        System.out.println("test finish!");
    }

}
