package com.sean.leetcode.LeetCodeInterview1712;

/**
 * @Author xionghaiyang
 * @Date 2026-03-25 18:10
 * @Description https://leetcode.cn/problems/binode-lcci
 * 面试题 17.12. BiNode
 * 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。
 * 实现一个方法，把二叉搜索树转换为单向链表，要求依然符合二叉搜索树的性质，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
 * 返回转换后的单向链表的头节点。
 * 注意：本题相对原题稍作改动
 * 节点数量不会超过 100000。
 */
public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public class Info {
        private TreeNode head;
        private TreeNode tail;

        public Info(TreeNode head, TreeNode tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    public TreeNode convertBiNode(TreeNode root) {
        return process(root).head;
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return new Info(null, null);
        }
        TreeNode head = null, tail = null;
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        if (leftInfo.head != null) {
            head = leftInfo.head;
            leftInfo.tail.right = root;
            root.left = null;
        } else {
            head = root;
        }
        if (rightInfo.head != null) {
            root.right = rightInfo.head;
            tail = rightInfo.tail;
        } else {
            tail = root;
        }
        return new Info(head, tail);
    }

}
