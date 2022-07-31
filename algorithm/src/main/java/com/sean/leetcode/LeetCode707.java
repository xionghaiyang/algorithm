package com.sean.leetcode;

public class LeetCode707 {

    public class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    class MyLinkedList {

        public int size;
        public ListNode head;

        public MyLinkedList() {
            size = 0;
            head = new ListNode(0);
        }

        public int get(int index) {
            if (index < 0 || index >= size) {
                return -1;
            }
            ListNode cur = head;
            for (int i = 0; i < index + 1; i++) {
                cur = cur.next;
            }
            return cur.val;
        }

        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        public void addAtTail(int val) {
            addAtIndex(size, val);
        }

        public void addAtIndex(int index, int val) {
            if (index > size) {
                return;
            }
            if (index < 0) {
                index = 0;
            }
            size++;
            ListNode pre = head;
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            ListNode addListNode = new ListNode(val);
            addListNode.next = pre.next;
            pre.next = addListNode;
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            size--;
            ListNode pre = head;
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            pre.next = pre.next.next;
        }
    }
}
