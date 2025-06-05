package com.sean.leetcode.LeetCode24;

/**
 * @Author xionghaiyang
 * @Date 2025-06-05 08:16
 * @Description https://leetcode.cn/problems/swap-nodes-in-pairs
 * 24. 两两交换链表中的节点
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
 * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
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

    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode dummy = dummyHead;
        while (dummy.next != null && dummy.next.next != null) {
            ListNode node1 = dummy.next;
            ListNode node2 = node1.next;
            dummy.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            dummy = node1;
        }
        return dummyHead.next;
    }

}
