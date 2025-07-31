package com.sean.leetcode.LeetCodeInterview0206;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-07-31 16:09
 * @Description https://leetcode.cn/problems/palindrome-linked-list-lcci
 * 面试题 02.06. 回文链表
 * 编写一个函数，检查输入的链表是否是回文的。
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

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        for (int i = 0, j = list.size() - 1; i < j; i++, j--) {
            if (list.get(i).val != list.get(j).val) {
                return false;
            }
        }
        return true;
    }

}
