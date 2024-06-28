package com.sean.base.chapter12;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-26 21:27
 * @Description: 是否是完全二叉树
 */
public class Code01_IsCBT {

    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        //是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;

            if ( //如果遇到了不双全节点之后，又发现当前节点不是叶节点
                    (leaf && (l != null || r != null))
                            || (l == null && r != null)) {
                return false;
            }
            if (l != null) {
                queue.offer(l);
            }
            if (r != null) {
                queue.offer(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

    public boolean isCBT2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isCBT;
    }

    public class Info {
        public boolean isFull; //是否是满二叉树
        public boolean isCBT;//是否是完全二叉树
        public int height;

        public Info(boolean full, boolean cbt, int h) {
            isFull = full;
            isCBT = cbt;
            height = h;
        }

    }

    private Info process(Node x) {
        if (x == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        boolean isCBT = false;
        if (isFull) {
            isCBT = true;
        } else {
            //以x为头整棵树，不满
            if (leftInfo.isCBT && rightInfo.isCBT) {
                if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }
                if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }
                if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
                    isCBT = true;
                }
            }
        }
        return new Info(isFull, isCBT, height);
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
        Code01_IsCBT solution = new Code01_IsCBT();
        System.out.println("test begin!");
        boolean success = true;
        for (int i = 0; i < testTimes; i++) {
            Node head = solution.generateRandomBST(maxLevel, maxValue);
            if (solution.isCBT1(head) != solution.isCBT2(head)) {
                success = false;
                break;
            }
        }
        System.out.println(success ? "Nice!" : "Oops!");
        System.out.println("test finish!");
    }

}
