package com.sean.leetcode.LeetCodeInterview0201;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-22 08:41
 * @Description: https://leetcode.cn/problems/remove-duplicate-node-lcci
 * 面试题 02.01. 移除重复节点
 * 编写代码，移除未排序链表中的重复节点。
 * 保留最开始出现的节点。
 * 链表长度在[0, 20000]范围内。
 */
public class Solution {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return head;
        }
        Set<Integer> set = new HashSet<>();
        set.add(head.val);
        ListNode cur = head;
        while (cur.next != null) {
            if (set.contains(cur.next.val)) {
                cur.next = cur.next.next;
            } else {
                set.add(cur.next.val);
                cur = cur.next;
            }
        }
        return head;
    }

    public ListNode removeDuplicateNodes1(ListNode head) {
        ListNode pre = head;
        while (pre != null) {
            ListNode cur = pre;
            while (cur.next != null) {
                if (cur.next.val == pre.val) {
                    cur.next = cur.next.next;
                } else {
                    cur = cur.next;
                }
            }
            pre = pre.next;
        }
        return head;
    }

}
