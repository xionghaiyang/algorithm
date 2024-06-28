package com.sean.base.chapter12;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-27 20:07
 * @Description: 是否是满二叉树
 */
public class Code04_IsFull {

    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }

    }

    //第一种方法
    //手机整棵树的高度h和节点数n
    //只有满的二叉树满足2 ^ h - == n
    public boolean isFull1(Node head) {
        if (head == null) {
            return true;
        }
        Info1 all = process1(head);
        return (1 << all.height) - 1 == all.nodes;
    }

    public class Info1 {
        public int height;
        public int nodes;

        public Info1(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    private Info1 process1(Node head) {
        if (head == null) {
            return new Info1(0, 0);
        }
        Info1 leftInfo = process1(head.left);
        Info1 rightInfo = process1(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new Info1(height, nodes);
    }

    //第二种方法
    //收集子树是否满二叉树
    //收集子树的高度
    //左树满&&右树满&&左右树高度一样->整棵树是满的
    public boolean isFull2(Node head) {
        return process2(head).isFull;
    }

    public class Info2 {
        public boolean isFull;
        public int height;

        public Info2(boolean isFull, int height) {
            this.isFull = isFull;
            this.height = height;
        }
    }

    private Info2 process2(Node head) {
        if (head == null) {
            return new Info2(true, 0);
        }
        Info2 leftInfo = process2(head.left);
        Info2 rightInfo = process2(head.right);
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info2(isFull, height);
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
        System.out.println("test begin!");
        Code04_IsFull solution = new Code04_IsFull();
        for (int i = 0; i < testTimes; i++) {
            Node head = solution.generateRandomBST(maxLevel, maxValue);
            if (solution.isFull1(head) != solution.isFull2(head)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
        System.out.println("test finish!");
    }
}
