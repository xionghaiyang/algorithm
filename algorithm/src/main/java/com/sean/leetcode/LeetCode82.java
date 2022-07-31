package com.sean.leetcode;

import java.util.HashSet;
import java.util.Stack;

/**
 * 删除排序链表中的重复元素 II
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
 */
public class LeetCode82 {

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

    public static ListNode deleteDuplicates(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        HashSet<Integer> hashSet = new HashSet<>();
        ListNode currentNode = head;
        while (currentNode != null) {
            if (stack.isEmpty() || stack.peek().val != currentNode.val) {
                stack.push(currentNode);
            } else if (stack.peek().val == currentNode.val) {
                hashSet.add(currentNode.val);
            }
            currentNode = currentNode.next;
        }
        ListNode res = null;
        while (!stack.isEmpty()) {
            ListNode listNode = stack.pop();
            if (!hashSet.contains(listNode.val)) {
                listNode.next = res;
                res = listNode;
            }
        }
        return res;
    }

    private static ListNode createListNode(int[] arr) {
        ListNode dummyHead = new ListNode();
        ListNode cur = dummyHead;
        for (int i : arr) {
            ListNode nextNode = new ListNode(i);
            cur.next = nextNode;
            cur = cur.next;
        }
        return dummyHead.next;
    }

    private static void printListNode(ListNode listNode) {
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public static void main(String[] args) {
        printListNode(deleteDuplicates(createListNode(new int[]{1,2,3,4,4,5})));
        System.out.println("==============================================");
        printListNode(deleteDuplicates(createListNode(new int[]{1,1,1,2,3})));
    }
}
