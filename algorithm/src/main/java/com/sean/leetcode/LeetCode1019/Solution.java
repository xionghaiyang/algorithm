package com.sean.leetcode.LeetCode1019;

import java.util.Stack;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-10 08:12
 * @Description: https://leetcode.cn/problems/next-greater-node-in-linked-list/
 * 1019. 链表中的下一个更大节点
 * 给定一个长度为 n 的链表 head
 * 对于列表中的每个节点，查找下一个 更大节点 的值。
 * 也就是说，对于每个节点，找到它旁边的第一个节点的值，这个节点的值 严格大于 它的值。
 * 返回一个整数数组 answer ，其中 answer[i] 是第 i 个节点( 从1开始 )的下一个更大的节点的值。
 * 如果第 i 个节点没有下一个更大的节点，设置 answer[i] = 0 。
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

    public int[] nextLargerNodes(ListNode head) {
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        int[] arr = new int[length];
        node = head;
        for (int i = 0; i < length && node != null; i++, node = node.next) {
            arr[i] = node.val;
        }
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                res[stack.peek()] = arr[i];
                stack.pop();
            }
            stack.push(i);
        }
        return res;
    }

}
