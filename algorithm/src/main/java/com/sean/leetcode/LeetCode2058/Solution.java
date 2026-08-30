package com.sean.leetcode.LeetCode2058;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-31 05:47
 * @Description: https://leetcode.cn/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points
 * 2058. 找出临界点之间的最小和最大距离
 * 链表中的 临界点 定义为一个 局部极大值点 或 局部极小值点 。
 * 如果当前节点的值 严格大于 前一个节点和后一个节点，那么这个节点就是一个  局部极大值点 。
 * 如果当前节点的值 严格小于 前一个节点和后一个节点，那么这个节点就是一个  局部极小值点 。
 * 注意：节点只有在同时存在前一个节点和后一个节点的情况下，才能成为一个 局部极大值点 / 极小值点 。
 * 给你一个链表 head ，返回一个长度为 2 的数组 [minDistance, maxDistance] ，其中 minDistance 是任意两个不同临界点之间的最小距离，maxDistance 是任意两个不同临界点之间的最大距离。
 * 如果临界点少于两个，则返回 [-1，-1] 。
 * 链表中节点的数量在范围 [2, 10^5] 内
 * 1 <= Node.val <= 10^5
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

    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int pre = Integer.MIN_VALUE / 2, first = 0, minDis = Integer.MAX_VALUE;
        ListNode a = head, b = head.next, c = head.next.next;
        for (int i = 1; c != null; i++) {
            if ((a.val < b.val && b.val > c.val) || (a.val > b.val && b.val < c.val)) {
                if (first == 0) {
                    first = i;
                }
                minDis = Math.min(minDis, i - pre);
                pre = i;
            }
            a = b;
            b = c;
            c = c.next;
        }
        if (first >= pre) {
            return new int[]{-1, -1};
        }
        return new int[]{minDis, pre - first};
    }

}
