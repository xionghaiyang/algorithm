package com.sean.base.chapter09;

import java.util.ArrayList;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-24 19:53
 * @Description: 链表中点
 */
public class Code01_LinkedListMid {

    public static class Node {
        public int value;
        public Node next;

        public Node(int v) {
            value = v;
        }
    }

    //输入链表头节点，奇数长度返回中点，偶数长度返回上中点
    public Node midOrUpMidNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        //链表有3个点或以上
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public Node right1(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 1) / 2);
    }

    //输入链表头节点，奇数长度返回中点，偶数长度返回下中点
    public Node midOrDownMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public Node right2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get(arr.size() / 2);
    }

    //输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
    public Node midOrUpMidPreNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public Node right3(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 1) / 2 - 1);
    }

    //输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
    public Node midOrDownMidPreNode(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        if (head.next.next == null) {
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public Node right4(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get(arr.size() / 2 - 1);
    }

    public static void main(String[] args) {
        Node test = new Node(0);
        Node cur = test;
        for (int i = 1; i < 9; i++) {
            cur.next = new Node(i);
            cur = cur.next;
        }
        Node res1 = null;
        Node res2 = null;
        Code01_LinkedListMid solution = new Code01_LinkedListMid();

        res1 = solution.midOrDownMidNode(test);
        res2 = solution.right1(test);
        System.out.println(res1 != null ? res1.value : "无");
        System.out.println(res2 != null ? res2.value : "无");

        res1 = solution.midOrDownMidNode(test);
        res2 = solution.right2(test);
        System.out.println(res1 != null ? res1.value : "无");
        System.out.println(res2 != null ? res2.value : "无");

        res1 = solution.midOrUpMidPreNode(test);
        res2 = solution.right3(test);
        System.out.println(res1 != null ? res1.value : "无");
        System.out.println(res2 != null ? res2.value : "无");

        res1 = solution.midOrDownMidPreNode(test);
        res2 = solution.right4(test);
        System.out.println(res1 != null ? res1.value : "无");
        System.out.println(res2 != null ? res2.value : "无");
    }

}
