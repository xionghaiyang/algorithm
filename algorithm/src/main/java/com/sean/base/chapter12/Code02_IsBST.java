package com.sean.base.chapter12;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-26 22:04
 * @Description: 判断是否是二叉搜索树
 */
public class Code02_IsBST {

    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        List<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }

    private void in(Node head, List<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public boolean isBST2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    public class Info {
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    private Info process(Node x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int max = x.value;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
        }
        int min = x.value;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
        }
        boolean isBST = true;
        if (leftInfo != null && !leftInfo.isBST) {
            isBST = false;
        }
        if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        }
        if (leftInfo != null && leftInfo.max >= x.value) {
            isBST = false;
        }
        if (rightInfo != null && rightInfo.min <= x.value) {
            isBST = false;
        }
        return new Info(isBST, max, min);
    }

    public Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    public Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        Code02_IsBST solution = new Code02_IsBST();
        boolean succeed = true;
        System.out.println("test begin!");
        for (int i = 0; i < testTimes; i++) {
            Node head = solution.generateRandomBST(maxLevel, maxValue);
            if (solution.isBST1(head) != solution.isBST2(head)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
        System.out.println("test finish!");
    }
}
