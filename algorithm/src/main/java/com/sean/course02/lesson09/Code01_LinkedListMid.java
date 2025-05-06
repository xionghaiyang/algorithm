package com.sean.course02.lesson09;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-05-06 20:22
 * @Description 快慢指针
 */
public class Code01_LinkedListMid {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    //输入链表头节点，奇数长度返回中点，偶数长度返回上中点
    public static Node midOrUpMidNode(Node head) {
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

    public static Node right1(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        List<Node> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        return list.get((list.size() - 1) / 2);
    }

    //输入链表头节点，奇数长度返回中点，偶数长度返回下中点
    public static Node midOrDownMidNode(Node head) {
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

    public static Node right2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        List<Node> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        return list.get(list.size() / 2);
    }

    //输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
    public static Node midOrUpMidPreNode(Node head) {
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

    public static Node right3(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node cur = head;
        List<Node> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        return list.get((list.size() - 3) / 2);
    }

    //输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
    public static Node midOrDownMidPreNode(Node head) {
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

    public static Node right4(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node cur = head;
        List<Node> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        return list.get((list.size() - 2) / 2);
    }

    public static void main(String[] args) {
        Node node = new Node(0);
        node.next = new Node(1);
        node.next.next = new Node(2);
        node.next.next.next = new Node(3);
        node.next.next.next.next = new Node(4);
        node.next.next.next.next.next = new Node(5);
        node.next.next.next.next.next.next = new Node(6);
        node.next.next.next.next.next.next.next = new Node(7);

        Node res1 = null;
        Node res2 = null;

        res1 = midOrUpMidNode(node);
        res2 = right1(node);
        System.out.println(res1.equals(res2));
        System.out.println(res1.value + "," + res2.value);

        res1 = midOrDownMidNode(node);
        res2 = right2(node);
        System.out.println(res1.equals(res2));
        System.out.println(res1.value + "," + res2.value);

        res1 = midOrUpMidPreNode(node);
        res2 = right3(node);
        System.out.println(res1.equals(res2));
        System.out.println(res1.value + "," + res2.value);

        res1 = midOrDownMidPreNode(node);
        res2 = right4(node);
        System.out.println(res1.equals(res2));
        System.out.println(res1.value + "," + res2.value);
    }

}
