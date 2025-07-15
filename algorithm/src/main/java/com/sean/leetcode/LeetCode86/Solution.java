package com.sean.leetcode.LeetCode86;

/**
 * @Author xionghaiyang
 * @Date 2025-07-15 12:24
 * @Description https://leetcode.cn/problems/partition-list
 * 86. 分隔链表
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 * 链表中节点的数目在范围 [0, 200] 内
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
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

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyLeft = new ListNode();
        ListNode dummyRight = new ListNode();
        ListNode cur = head;
        ListNode left = dummyLeft;
        ListNode right = dummyRight;
        while (cur != null) {
            if (cur.val < x) {
                left.next = cur;
                left = left.next;
            } else {
                right.next = cur;
                right = right.next;
            }
            cur = cur.next;
        }
        left.next = dummyRight.next;
        right.next = null;
        return dummyLeft.next;
    }

}
