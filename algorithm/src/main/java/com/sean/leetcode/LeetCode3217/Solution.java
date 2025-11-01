package com.sean.leetcode.LeetCode3217;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2025-11-01 12:22
 * @Description https://leetcode.cn/problems/delete-nodes-from-linked-list-present-in-array
 * 3217. 从链表中移除在数组中存在的节点
 * 给你一个整数数组 nums 和一个链表的头节点 head。
 * 从链表中移除所有存在于 nums 中的节点后，返回修改后的链表的头节点。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * nums 中的所有元素都是唯一的。
 * 链表中的节点数在 [1, 10^5] 的范围内。
 * 1 <= Node.val <= 10^5
 * 输入保证链表中至少有一个值没有在 nums 中出现过。
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

    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        ListNode dummyHead = new ListNode(0, head);
        ListNode pre = dummyHead;
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur.val)) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return dummyHead.next;
    }

}
