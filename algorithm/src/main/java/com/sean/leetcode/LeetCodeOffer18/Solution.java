package com.sean.leetcode.LeetCodeOffer18;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-28 22:32
 * @Description: https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/?plan=lcof&plan_progress=zq3t7ii
 * 剑指 Offer 18. 删除链表的节点
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * 返回删除后的链表的头节点。
 */
public class Solution {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) {
            return head.next;
        }
        ListNode pre = head, cur = head.next;
        while (cur != null && cur.val != val) {
            pre = cur;
            cur = cur.next;
        }
        if (cur != null) {
            pre.next = cur.next;
        }
        return head;
    }

}
