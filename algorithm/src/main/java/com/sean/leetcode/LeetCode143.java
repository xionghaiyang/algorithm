package com.sean.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 重排链表
 * https://leetcode-cn.com/problems/reorder-list/
 */
public class LeetCode143 {

    private static class ListNode {
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

    public static void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode currentNode = head;
        while (currentNode != null) {
            list.add(currentNode);
            currentNode = currentNode.next;
        }
        int left = 0, right = list.size() - 1;
        while (left < right) {
            list.get(left).next = list.get(right);
            left++;
            if (left == right) {
                break;
            }
            list.get(right).next = list.get(left);
            right--;
        }
        list.get(left).next = null;
    }

}
