package com.sean.leetcode.LeetCodeOffer06;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-09 21:49
 * @Description: https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/?plan=lcof&plan_progress=zq3t7ii
 * 剑指 Offer 06. 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 */
public class Solution {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public int[] reversePrint(ListNode head) {
        int n = getLength(head);
        int[] res = new int[n];
        int index = n - 1;
        ListNode cur = head;
        while (cur != null) {
            res[index--] = cur.val;
            cur = cur.next;
        }
        return res;
    }

    private int getLength(ListNode head) {
        if (head == null) {
            return 0;
        }
        ListNode cur = head;
        int res = 0;
        while (cur != null) {
            cur = cur.next;
            res++;
        }
        return res;
    }

}
