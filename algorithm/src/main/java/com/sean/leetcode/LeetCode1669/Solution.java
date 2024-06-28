package com.sean.leetcode.LeetCode1669;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-30 09:19
 * @Description: https://leetcode.cn/problems/merge-in-between-linked-lists/
 * 1669. 合并两个链表
 * 给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。
 * 请你将 list1 中下标从 a 到 b 的全部节点都删除，并将list2 接在被删除节点的位置。
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

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode cur = list1;
        for (int i = 1; i < a; i++) {
            cur = cur.next;
        }
        ListNode leftNode = cur;
        for (int i = a; i <= b; i++) {
            cur = cur.next;
        }
        ListNode rightNode = cur.next;
        leftNode.next = list2;
        while (leftNode.next != null) {
            leftNode = leftNode.next;
        }
        leftNode.next = rightNode;
        return list1;
    }

}
