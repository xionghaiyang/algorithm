package com.sean.leetcode.LeetCode876;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-05 18:34
 * @Description: https://leetcode.cn/problems/middle-of-the-linked-list
 * 876. 链表的中间结点
 * 给你单链表的头结点 head ，请你找出并返回链表的中间结点。
 * 如果有两个中间结点，则返回第二个中间结点。
 * 链表的结点数范围是 [1, 100]
 * 1 <= Node.val <= 100
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

    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
