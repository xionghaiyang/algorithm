package com.sean.course02.lesson03;

/**
 * @Author xionghaiyang
 * @Date 2025-03-28 21:47
 * @Description 在链表中，把给定值都删除
 */
public class Code02_DeleteGivenValue {

    public class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public Node removeValue(Node head, int num) {
        //head来到第一个不需要删的位置
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

}
