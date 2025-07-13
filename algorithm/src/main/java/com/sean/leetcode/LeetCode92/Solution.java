package com.sean.leetcode.LeetCode92;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author xionghaiyang
 * @Date 2025-07-13 19:39
 * @Description https://leetcode.cn/problems/reverse-linked-list-ii
 * 92. 反转链表 II
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
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

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode dummy = dummyHead;
        for (int i = 1; i < left; i++) {
            dummy = dummy.next;
        }
        ListNode leftNode = dummy;
        Deque<ListNode> stack = new ArrayDeque<>();
        for (int i = left; i <= right; i++) {
            dummy = dummy.next;
            stack.push(dummy);
        }
        ListNode rightNode = dummy.next;
        while (!stack.isEmpty()) {
            leftNode.next = stack.pop();
            leftNode = leftNode.next;
        }
        leftNode.next = rightNode;
        return dummyHead.next;
    }

    public ListNode reverseBetween1(ListNode head, int left, int right) {
        ListNode dummyHead = new ListNode(0, head);
        ListNode dummy = dummyHead;
        for (int i = 1; i < left; i++) {
            dummy = dummy.next;
        }
        ListNode pre = null;
        ListNode cur = dummy.next;
        for (int i = left; i <= right; i++) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        dummy.next.next = cur;
        dummy.next = pre;
        return dummyHead.next;
    }

}
