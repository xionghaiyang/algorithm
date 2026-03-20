package com.sean.leetcode.LeetCodeInterview1010;

/**
 * @Author xionghaiyang
 * @Date 2026-03-20 19:08
 * @Description https://leetcode.cn/problems/rank-from-stream-lcci
 * 面试题 10.10. 数字流的秩
 * 假设你正在读取一串整数。
 * 每隔一段时间，你希望能找出数字 x 的秩(小于或等于 x 的值的个数)。
 * 请实现数据结构和算法来支持这些操作，也就是说：
 * 实现 track(int x) 方法，每读入一个数字都会调用该方法；
 * 实现 getRankOfNumber(int x) 方法，返回小于或等于 x 的值的个数。
 * 注意：本题相对原题稍作改动
 * x <= 50000
 * track 和 getRankOfNumber 方法的调用次数均不超过 2000 次
 */
public class StreamRank1 {

    public class Node {
        private int val;
        private int count = 1;
        private int leftCount = 0;
        private Node left = null;
        private Node right = null;

        public Node(int val) {
            this.val = val;
        }
    }

    private Node node = null;

    public StreamRank1() {
    }

    public void track(int x) {
        node = insert(node, x);
    }

    public int getRankOfNumber(int x) {
        return getRankOfNumber(node, x);
    }

    private Node insert(Node node, int x) {
        if (node == null) {
            return new Node(x);
        } else if (x == node.val) {
            node.count++;
        } else if (x < node.val) {
            node.leftCount++;
            node.left = insert(node.left, x);
        } else {
            node.right = insert(node.right, x);
        }
        return node;
    }

    private int getRankOfNumber(Node node, int x) {
        if (node == null) {
            return 0;
        } else if (x == node.val) {
            return node.count + node.leftCount;
        } else if (x < node.val) {
            return getRankOfNumber(node.left, x);
        } else {
            return node.count + node.leftCount + getRankOfNumber(node.right, x);
        }
    }

}
