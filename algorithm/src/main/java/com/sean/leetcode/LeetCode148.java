package com.sean.leetcode;

/**
 * 排序链表
 * https://leetcode-cn.com/problems/sort-list/
 */
public class LeetCode148 {

    private static class ListNode {
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

    public static ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    //自顶而下
    private static ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode res = merge(list1, list2);
        return res;
    }

    private static ListNode merge(ListNode head, ListNode tail) {
        ListNode dummyHead = new ListNode();
        ListNode dummy = dummyHead, headNode = head, tailNode = tail;
        while (headNode != null && tailNode != null) {
            if (headNode.val <= tailNode.val) {
                dummy.next = headNode;
                headNode = headNode.next;
            } else {
                dummy.next = tailNode;
                tailNode = tailNode.next;
            }
            dummy = dummy.next;
        }
        if (headNode != null) {
            dummy.next = headNode;
        } else if (tailNode != null) {
            dummy.next = tailNode;
        }
        return dummyHead.next;
    }

    //自底而上
    public static ListNode sortList1(ListNode head) {
        if (head == null) {
            return head;
        }
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        ListNode dummyHead = new ListNode(0, head);
        for (int sunLength = 1; sunLength < length; sunLength <<= 1) {
            ListNode pre = dummyHead, cur = dummyHead.next;
            while (cur != null) {
                ListNode headNode = cur;
                for (int i = 1; i < sunLength && cur.next != null; i++) {
                    cur = cur.next;
                }
                ListNode tailNode = cur.next;
                cur.next = null;
                cur = tailNode;
                for (int i = 1; i < sunLength && cur != null && cur.next != null; i++) {
                    cur = cur.next;
                }
                ListNode next = null;
                if (cur != null) {
                    next = cur.next;
                    cur.next = null;
                }
                ListNode mergeNode = merge(headNode, tailNode);
                pre.next = mergeNode;
                while (pre.next != null) {
                    pre = pre.next;
                }
                cur = next;

            }
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }


}
