package com.sean.leetcode.LeetCode61;

/**
 * @Author xionghaiyang
 * @Date 2025-07-14 20:12
 * @Description https://leetcode.cn/problems/rotate-list
 * 61. 旋转链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 10^9
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

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int length = 1;
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
            length++;
        }
        k = length - (k % length);
        if (k == length) {
            return head;
        }
        cur.next = head;
        while (k > 0) {
            cur = cur.next;
            k--;
        }
        ListNode res = cur.next;
        cur.next = null;
        return res;
    }

}
