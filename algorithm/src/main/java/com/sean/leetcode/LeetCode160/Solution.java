package com.sean.leetcode.LeetCode160;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2025-06-03 09:34
 * @Description https://leetcode.cn/problems/intersection-of-two-linked-lists
 * 160. 相交链表
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。
 * 如果两个链表不存在相交节点，返回 null 。
 * 题目数据 保证 整个链式结构中不存在环。
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 * listA 中节点数目为 m
 * listB 中节点数目为 n
 * 1 <= m, n <= 3 * 10^4
 * 1 <= Node.val <= 10^5
 * 0 <= skipA <= m
 * 0 <= skipB <= n
 * 如果 listA 和 listB 没有交点，intersectVal 为 0
 * 如果 listA 和 listB 有交点，intersectVal == listA[skipA] == listB[skipB]
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
        Set<ListNode> set = new HashSet<>();
        ListNode cur = headA;
        while (cur != null) {
            set.add(cur);
            cur = cur.next;
        }
        cur = headB;
        while (cur != null) {
            if (set.contains(cur)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        int lengthA = getLength(headA);
        int lengthB = getLength(headB);
        ListNode small = lengthA <= lengthB ? headA : headB;
        ListNode big = small == headA ? headB : headA;
        int diff = Math.abs(lengthA - lengthB);
        while (diff > 0) {
            big = big.next;
            diff--;
        }
        while (big != null) {
            if (big == small) {
                return big;
            } else {
                big = big.next;
                small = small.next;
            }
        }
        return null;
    }

    private int getLength(ListNode head) {
        if (head == null) {
            return 0;
        }
        int res = 0;
        ListNode cur = head;
        while (cur != null) {
            res++;
            cur = cur.next;
        }
        return res;
    }

}
