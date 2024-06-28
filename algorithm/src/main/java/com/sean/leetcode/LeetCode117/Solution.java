package com.sean.leetcode.LeetCode117;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-12 15:01
 * @Description: https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii/?envType=study-plan-v2&envId=top-interview-150
 * 117. 填充每个节点的下一个右侧节点指针 II
 * 给定一个二叉树：
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
 * 如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。
 * 初始状态下，所有 next 指针都被设置为 NULL 。
 */
public class Solution {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            Node last = null;
            for (int i = 0; i < n; i++) {
                Node node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (last != null) {
                    last.next = node;
                }
                last = node;
            }
        }
        return root;
    }

    private Node last = null, nextStart = null;

    public Node connect1(Node root) {
        if (root == null) {
            return null;
        }
        Node start = root;
        while (start != null) {
            last = null;
            nextStart = null;
            for (Node node = start; node != null; node = node.next) {
                if (node.left != null) {
                    process(node.left);
                }
                if (node.right != null) {
                    process(node.right);
                }
            }
            start = nextStart;
        }
        return root;
    }

    private void process(Node node) {
        if (last != null) {
            last.next = node;
        }
        if (nextStart == null) {
            nextStart = node;
        }
        last = node;
    }

}
