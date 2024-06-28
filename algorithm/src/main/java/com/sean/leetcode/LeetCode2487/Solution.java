package com.sean.leetcode.LeetCode2487;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-03 13:24
 * @Description: https://leetcode.cn/problems/remove-nodes-from-linked-list/
 * 2487. 从链表中移除节点
 * 给你一个链表的头节点 head 。
 * 移除每个右侧有一个更大数值的节点。
 * 返回修改后链表的头节点 head 。
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

    public ListNode removeNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        head.next = removeNodes(head.next);
        if (head.next != null && head.val < head.next.val) {
            return head.next;
        } else {
            return head;
        }
    }

    public ListNode removeNodes1(ListNode head) {
        Deque<ListNode> stack = new ArrayDeque<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        while (!stack.isEmpty()) {
            if (head == null || stack.peek().val >= head.val) {
                stack.peek().next = head;
                head = stack.peek();
            }
            stack.pop();
        }
        return head;
    }

    public ListNode removeNodes2(ListNode head) {
        head = reverse(head);
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val > cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return reverse(head);
    }

    private ListNode reverse(ListNode head) {
        ListNode dummy = new ListNode();
        while (head != null) {
            ListNode cur = head;
            head = head.next;
            cur.next = dummy.next;
            dummy.next = cur;
        }
        return dummy.next;
    }

}
