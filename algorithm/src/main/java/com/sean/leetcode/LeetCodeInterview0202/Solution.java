package com.sean.leetcode.LeetCodeInterview0202;

/**
 * @Author xionghaiyang
 * @Date 2025-07-30 12:21
 * @Description https://leetcode.cn/problems/kth-node-from-end-of-list-lcci
 * 面试题 02.02. 返回倒数第 k 个节点
 * 实现一种算法，找出单向链表中倒数第 k 个节点。
 * 返回该节点的值。
 * 注意：本题相对原题稍作改动
 * 给定的 k 保证是有效的。
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

    public int kthToLast(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        while (k-- > 0 && fast != null) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow.val;
    }

}
