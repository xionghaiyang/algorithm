package com.sean.leetcode.LeetCodeOffer24;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-09 21:59
 * @Description: https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/?plan=lcof&plan_progress=zq3t7ii
 * 剑指 Offer 24. 反转链表
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 */
public class Solution {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

}
