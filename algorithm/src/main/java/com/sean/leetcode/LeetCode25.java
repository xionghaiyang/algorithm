package com.sean.leetcode;

import com.sun.xml.internal.ws.addressing.WsaTubeHelperImpl;

import java.util.Stack;

/**
 * K 个一组翻转链表
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 */
public class LeetCode25 {

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

    public static ListNode reverseKGroup(ListNode head, int k) {
        Stack<ListNode> nodeStack = new Stack<>();
        ListNode dummyHead = new ListNode();
        ListNode currentNode = head, dummy = dummyHead;
        int count = 0;
        while (currentNode != null) {
            nodeStack.push(currentNode);
            currentNode = currentNode.next;
            count++;
            if (count % k == 0) {
                while (!nodeStack.isEmpty()) {
                    dummy.next = nodeStack.pop();
                    dummy = dummy.next;
                }
            }
        }
        if (count % k != 0) {
            Stack<ListNode> stack = new Stack<>();
            while (!nodeStack.isEmpty()) {
                stack.push(nodeStack.pop());
            }
            while (!stack.isEmpty()) {
                dummy.next = stack.pop();
                dummy = dummy.next;
            }
        }
        dummy.next = null;
        return dummyHead.next;
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

    public static void main(String[] args) {
        ListNode listNode = reverseKGroup(createListNode(new int[]{1, 2, 3, 4, 5}), 2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

}
