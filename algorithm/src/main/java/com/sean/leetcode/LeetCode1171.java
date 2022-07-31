package com.sean.leetcode;

/**
 * 从链表中删去总和值为零的连续节点
 * https://leetcode-cn.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
 */
public class LeetCode1171 {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode removeZeroSumSublists(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        while (pre != null) {
            ListNode cur = pre.next;
            int sum = 0;
            while (cur != null) {
                sum += cur.val;
                if (sum == 0) {
                    pre.next = cur.next;
                    break;
                } else {
                    cur= cur.next;
                }
            }
            if(cur == null){
                pre = pre.next;
            }
        }
        return dummyHead.next;
    }

}


