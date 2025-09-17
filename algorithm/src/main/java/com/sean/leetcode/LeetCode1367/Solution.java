package com.sean.leetcode.LeetCode1367;

/**
 * @Author xionghaiyang
 * @Date 2025-09-17 19:32
 * @Description https://leetcode.cn/problems/linked-list-in-binary-tree
 * 1367. 二叉树中的链表
 * 给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。
 * 如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。
 * 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
 * 二叉树和链表中的每个节点的值都满足 1 <= node.val <= 100 。
 * 链表包含的节点数目在 1 到 100 之间。
 * 二叉树包含的节点数目在 1 到 2500 之间。
 */
public class Solution {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

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

    private ListNode head;

    public boolean isSubPath(ListNode head, TreeNode root) {
        this.head = head;
        return dfs(head, root);
    }

    private boolean dfs(ListNode s, TreeNode node) {
        if (s == null) {
            return true;
        }
        if (node == null) {
            return false;
        }
        return (s.val == node.val && (dfs(s.next, node.left) || dfs(s.next, node.right)))
                || (s == head && (dfs(head, node.left) || dfs(head, node.right)));
    }

}
