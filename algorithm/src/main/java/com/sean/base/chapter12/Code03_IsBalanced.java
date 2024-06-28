package com.sean.base.chapter12;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-27 19:43
 * @Description: 是否是平衡二叉树
 */
public class Code03_IsBalanced {

    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public boolean isBalanced1(Node head) {
        boolean[] res = new boolean[1];
        res[0] = true;
        process1(head, res);
        return res[0];
    }

    private int process1(Node head, boolean[] res) {
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

    public boolean isBalanced2(Node head) {
        return process(head).isBalanced;
    }

    public class Info {
        public boolean isBalanced;
        public int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    private Info process(Node x) {
        if (x == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
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

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        boolean succeed = true;
        Code03_IsBalanced solution = new Code03_IsBalanced();
        System.out.println("test begin!");
        for (int i = 0; i < testTimes; i++) {
            Node head = solution.generateRandomBST(maxLevel, maxValue);
            if (solution.isBalanced1(head) != solution.isBalanced2(head)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
        System.out.println("test finish!");
    }

}
