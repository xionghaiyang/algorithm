package com.sean.course01.lesson06;

import java.util.PriorityQueue;

/**
 * @Author xionghaiyang
 * @Date 2025-03-23 21:49
 * @Description https://leetcode.cn/problems/merge-k-sorted-lists
 * 合并多个有序链表
 */
public class Code01_MergeKSortedLists {

    public class ListNode {
        public int val;
        public ListNode next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
        int n = lists.length;
        for (int i = 0; i < n; i++) {
            if (lists[i] != null) {
                heap.add(lists[i]);
            }
        }
        if (heap.isEmpty()) {
            return null;
        }
        ListNode head = heap.poll();
        ListNode pre = head;
        if (pre.next != null) {
            heap.add(pre.next);
        }
        while (!heap.isEmpty()) {
            ListNode cur = heap.poll();
            pre.next = cur;
            pre = cur;
            if (cur.next != null) {
                heap.add(cur.next);
            }
        }
        return head;
    }

}
