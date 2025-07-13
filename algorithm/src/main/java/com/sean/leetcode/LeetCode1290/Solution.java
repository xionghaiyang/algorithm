package com.sean.leetcode.LeetCode1290;

/**
 * @Author xionghaiyang
 * @Date 2025-07-14 06:46
 * @Description https://leetcode.cn/problems/convert-binary-number-in-a-linked-list-to-integer
 * 1290. 二进制链表转整数
 * 给你一个单链表的引用结点 head。
 * 链表中每个结点的值不是 0 就是 1。
 * 已知此链表是一个整数数字的二进制表示形式。
 * 请你返回该链表所表示数字的 十进制值 。
 * 链表不为空。
 * 链表的结点总数不超过 30。
 * 每个结点的值不是 0 就是 1。
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

    public int getDecimalValue(ListNode head) {
        int res = 0;
        ListNode cur = head;
        while (cur != null) {
            res = (res << 1) + cur.val;
            cur = cur.next;
        }
        return res;
    }

}
