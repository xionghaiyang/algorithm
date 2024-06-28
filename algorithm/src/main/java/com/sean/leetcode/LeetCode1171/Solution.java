package com.sean.leetcode.LeetCode1171;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-12 11:52
 * @Description: https://leetcode.cn/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
 * 1171. 从链表中删去总和值为零的连续节点
 * 给你一个链表的头节点 head，请你编写代码，
 * 反复删去链表中由 总和 值为 0 的连续节点组成的序列，直到不存在这样的序列为止。
 * 删除完毕后，请你返回最终结果链表的头节点。
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

    public ListNode removeZeroSumSublists1(ListNode head) {
        ListNode dummyHead = new ListNode(0, head);
        Map<Integer, ListNode> map = new HashMap<>();
        int sum = 0;
        ListNode cur = dummyHead;
        while (cur != null) {
            sum += cur.val;
            map.put(sum, cur);
            cur = cur.next;
        }
        sum = 0;
        cur = dummyHead;
        while (cur != null) {
            sum += cur.val;
            cur.next = map.get(sum).next;
            cur = cur.next;
        }
        return dummyHead.next;
    }

    public ListNode removeZeroSumSublists(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0, head);
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
                    cur = cur.next;
                }
            }
            if (cur == null) {
                pre = pre.next;
            }
        }
        return dummyHead.next;
    }

}
