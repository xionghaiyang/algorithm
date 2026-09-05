package com.sean.leetcode.LeetCode143;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-05 18:09
 * @Description: https://leetcode.cn/problems/reorder-list
 * 143. 重排链表
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 链表的长度范围为 [1, 5 * 10^4]
 * 1 <= node.val <= 1000
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

    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        int left = 0, right = list.size() - 1;
        while (left < right) {
            list.get(left++).next = list.get(right);
            if (left == right) {
                break;
            }
            list.get(right--).next = list.get(left);
        }
        list.get(left).next = null;
    }

    public void reorderList1(ListNode head) {
        ListNode mid = middleNode(head);
        ListNode head2 = reverseList(mid);
        while (head2.next != null) {
            ListNode next = head.next;
            ListNode next2 = head2.next;
            head.next = head2;
            head2.next = next;
            head = next;
            head2 = next2;
        }
    }

    private ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

}
