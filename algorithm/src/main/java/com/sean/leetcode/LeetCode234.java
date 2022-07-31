package com.sean.leetcode;

import java.util.ArrayList;

/**
 * 回文链表
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 */
public class LeetCode234 {

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

    public static boolean isPalindrome(ListNode head) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        ListNode currentNode = head;
        while (currentNode != null) {
            arrayList.add(currentNode.val);
            currentNode = currentNode.next;
        }
        int left = 0, right = arrayList.size() - 1;
        while (left < right) {
            if (arrayList.get(left) != arrayList.get(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

}
