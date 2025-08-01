package com.sean.leetcode.LeetCodeInterview0207;

/**
 * @Author xionghaiyang
 * @Date 2025-08-01 10:35
 * @Description https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci
 * 面试题 02.07. 链表相交
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。
 * 如果两个链表没有交点，返回 null 。
 * 图示两个链表在节点 c1 开始相交：
 * 题目数据 保证 整个链式结构中不存在环。
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 * listA 中节点数目为 m
 * listB 中节点数目为 n
 * 0 <= m, n <= 3 * 10^4
 * 1 <= Node.val <= 10^5
 * 0 <= skipA <= m
 * 0 <= skipB <= n
 * 如果 listA 和 listB 没有交点，intersectVal 为 0
 * 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
 */
public class Solution {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int lengthA = 1;
        ListNode curA = headA;
        while (curA.next != null) {
            lengthA++;
            curA = curA.next;
        }
        int lengthB = 1;
        ListNode curB = headB;
        while (curB.next != null) {
            lengthB++;
            curB = curB.next;
        }
        if (curA != curB) {
            return null;
        }
        curA = lengthA > lengthB ? headA : headB;
        curB = curA == headA ? headB : headA;
        for (int i = 0; i < Math.abs(lengthA - lengthB); i++) {
            curA = curA.next;
        }
        while (curA != curB) {
            curA = curA.next;
            curB = curB.next;
        }
        return curA;
    }

}
