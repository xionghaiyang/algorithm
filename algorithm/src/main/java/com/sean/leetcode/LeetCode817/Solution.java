package com.sean.leetcode.LeetCode817;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-12 08:22
 * @Description: https://leetcode.cn/problems/linked-list-components/
 * 817. 链表组件
 * 给定链表头结点 head，该链表上的每个结点都有一个 唯一的整型值 。
 * 同时给定列表 nums，该列表是上述链表中整型值的一个子集。
 * 返回列表 nums 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表 nums 中）构成的集合。
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

    public int numComponents(ListNode head, int[] nums) {
        int res = 0;
        Set<Integer> set = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
        }
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur.val) && (cur.next == null || !set.contains(cur.next.val))) {
                res++;
            }
            cur = cur.next;
        }
        return res;
    }

}
