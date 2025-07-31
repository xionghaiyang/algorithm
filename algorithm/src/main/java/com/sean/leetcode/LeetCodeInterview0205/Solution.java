package com.sean.leetcode.LeetCodeInterview0205;

/**
 * @Author xionghaiyang
 * @Date 2025-07-31 15:33
 * @Description https://leetcode.cn/problems/sum-lists-lcci
 * 面试题 02.05. 链表求和
 * 给定两个用链表表示的整数，每个节点包含一个数位。
 * 这些数位是反向存放的，也就是个位排在链表首部。
 * 编写函数对这两个整数求和，并用链表形式返回结果。
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0), dummy = dummyHead, cur1 = l1, cur2 = l2;
        int carry = 0;
        while (cur1 != null || cur2 != null || carry != 0) {
            carry += (cur1 != null ? cur1.val : 0) + (cur2 != null ? cur2.val : 0);
            dummy.next = new ListNode(carry % 10);
            carry /= 10;
            dummy = dummy.next;
            if (cur1 != null) {
                cur1 = cur1.next;
            }
            if (cur2 != null) {
                cur2 = cur2.next;
            }
        }
        return dummyHead.next;
    }

}
