package com.sean.leetcode.LeetCode117;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-12 15:01
 * @Description: https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii
 * 117. 填充每个节点的下一个右侧节点指针 II
 * 给定一个二叉树：
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
 * 如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。
 * 初始状态下，所有 next 指针都被设置为 NULL 。
 * 树中的节点数在范围 [0, 6000] 内
 * -100 <= Node.val <= 100
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
            Node pre = new Node();
            for (int i = 0; i < n; i++) {
                Node node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                pre.next = node;
                pre = pre.next;
            }
            pre.next = null;
        }
        return root;
    }

    public Node connect1(Node root) {
        Node dummy = new Node();
        Node cur = root;
        while (cur != null) {
            dummy.next = null;
            Node next = dummy;
            while (cur != null) {
                if (cur.left != null) {
                    next.next = cur.left;
                    next = cur.left;
                }
                if (cur.right != null) {
                    next.next = cur.right;
                    next = cur.right;
                }
                cur = cur.next;
            }
            cur = dummy.next;
        }
        return root;
    }

}
