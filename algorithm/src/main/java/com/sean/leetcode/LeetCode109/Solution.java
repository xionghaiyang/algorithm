package com.sean.leetcode.LeetCode109;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-15 13:36
 * @Description: https://leetcode.cn/problems/convert-sorted-list-to-binary-search-tree
 * 109. 有序链表转换二叉搜索树
 * 给定一个单链表的头节点  head ，其中的元素 按升序排序 ，将其转换为 平衡 二叉搜索树。
 * head 中的节点数在[0, 2 * 10^4] 范围内
 * -10^5 <= Node.val <= 10^5
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

    public TreeNode sortedListToBST(ListNode head) {
        return buildTree(head, null);
    }

    private TreeNode buildTree(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }
        ListNode mid = getMedian(left, right);
        TreeNode root = new TreeNode(mid.val);
        root.left = buildTree(left, mid);
        root.right = buildTree(mid.next, right);
        return root;
    }

    private ListNode getMedian(ListNode left, ListNode right) {
        ListNode slow = left, fast = left;
        while (fast != right && fast.next != right) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
