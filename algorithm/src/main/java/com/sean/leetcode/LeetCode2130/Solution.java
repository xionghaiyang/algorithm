package com.sean.leetcode.LeetCode2130;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-15 10:26
 * @Description: https://leetcode.cn/problems/maximum-twin-sum-of-a-linked-list/?envType=study-plan-v2&envId=leetcode-75
 * 2130. 链表最大孪生和
 * 在一个大小为 n 且 n 为 偶数 的链表中，对于 0 <= i <= (n / 2) - 1 的 i ，
 * 第 i 个节点（下标从 0 开始）的孪生节点为第 (n-1-i) 个节点 。
 * 比方说，n = 4 那么节点 0 是节点 3 的孪生节点，节点 1 是节点 2 的孪生节点。
 * 这是长度为 n = 4 的链表中所有的孪生节点。
 * 孪生和 定义为一个节点和它孪生节点两者值之和。
 * 给你一个长度为偶数的链表的头节点 head ，请你返回链表的 最大孪生和 。
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

    public int pairSum(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode last = slow.next;
        while (last.next != null) {
            ListNode cur = last.next;
            last.next = cur.next;
            cur.next = slow.next;
            slow.next = cur;
        }
        int res = 0;
        ListNode x = head;
        ListNode y = slow.next;
        while (y != null) {
            res = Math.max(res, x.val + y.val);
            x = x.next;
            y = y.next;
        }
        return res;
    }

}
