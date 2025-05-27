package com.sean.course02.lesson12;

/**
 * @Author xionghaiyang
 * @Date 2025-05-27 11:27
 * @Description 是否是平衡二叉树
 */
public class Code03_IsBalanced {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isBalanced1(Node head) {
        boolean[] res = new boolean[1];
        res[0] = true;
        process1(head, res);
        return res[0];
    }

    private static int process1(Node head, boolean[] res) {
        if (!res[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, res);
        int rightHeight = process1(head.right, res);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            res[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static boolean isBalanced2(Node head) {
        return process(head).isBalanced;
    }

    public static class Info {
        public boolean isBalanced;
        public int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    private static Info process(Node head) {
        if (head == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = true;
        if (!leftInfo.isBalanced) {
            isBalanced = false;
        }
        if (!rightInfo.isBalanced) {
            isBalanced = false;
        }
        if (Math.abs(leftInfo.height - rightInfo.height) > 1) {
            isBalanced = false;
        }
        return new Info(isBalanced, height);
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
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != isBalanced2(head)) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
