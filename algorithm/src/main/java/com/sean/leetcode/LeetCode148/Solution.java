package com.sean.leetcode.LeetCode148;

/**
 * @Author xionghaiyang
 * @Date 2025-06-06 07:49
 * @Description https://leetcode.cn/problems/sort-list
 * 148. 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 链表中节点的数目在范围 [0, 5 * 10^4] 内
 * -10^5 <= Node.val <= 10^5
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

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode head2 = getMiddleNode(head);
        head = sortList(head);
        head2 = sortList(head2);
        return mergeTwoLists(head, head2);
    }

    //找到中间节点，并断开其与前一个节点的连接
    private ListNode getMiddleNode(ListNode head) {
        ListNode pre = head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        return slow;
    }

    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode();
        ListNode dummy = dummyHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                dummy.next = list1;
                list1 = list1.next;
            } else {
                dummy.next = list2;
                list2 = list2.next;
            }
            dummy = dummy.next;
        }
        dummy.next = list1 != null ? list1 : list2;
        return dummyHead.next;
    }

}
