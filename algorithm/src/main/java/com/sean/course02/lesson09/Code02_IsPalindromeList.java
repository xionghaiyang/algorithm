package com.sean.course02.lesson09;

import java.util.Stack;

/**
 * @Author xionghaiyang
 * @Date 2025-05-07 12:25
 * @Description 给定一个单链表的头节点head，请判断该链表是否为回文结构。
 */
public class Code02_IsPalindromeList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isPalindrome1(Node head) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node right = head.next;
        Node cur = head;
        while (cur.next != null && cur.next.next != null) {
            right = right.next;
            cur = cur.next.next;
        }
        Stack<Node> stack = new Stack<>();
        while (right != null) {
            stack.push(right);
            right = right.next;
        }
        while (!stack.isEmpty()) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static boolean isPalindrome3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node node1 = head;
        Node node2 = head;
        while (node2.next != null && node2.next.next != null) {
            //node1 -> mid
            node1 = node1.next;
            //node2 -> end
            node2 = node2.next.next;
        }
        node2 = node1.next;
        node1.next = null;
        Node node3 = null;
        while (node2 != null) {
            node3 = node2.next;
            node2.next = node1;
            node1 = node2;
            node2 = node3;
        }
        node3 = node1;
        node2 = head;
        boolean res = true;
        while (node1 != null && node2 != null) {
            if (node1.value != node2.value) {
                res = false;
                break;
            }
            node1 = node1.next;
            node2 = node2.next;
        }
        node1 = node3.next;
        node3.next = null;
        while (node1 != null) {
            node2 = node1.next;
            node1.next = node3;
            node3 = node1;
            node1 = node2;
        }
        return res;
    }

    private static void printLinkedList(Node node) {
        System.out.print("Linked List:");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + "| ");
        System.out.print(isPalindrome2(head) + "| ");
        System.out.println(isPalindrome3(head) + "| ");
        printLinkedList(head);
        System.out.println("============================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + "| ");
        System.out.print(isPalindrome2(head) + "| ");
        System.out.println(isPalindrome3(head) + "| ");
        printLinkedList(head);
        System.out.println("============================");


        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + "| ");
        System.out.print(isPalindrome2(head) + "| ");
        System.out.println(isPalindrome3(head) + "| ");
        printLinkedList(head);
        System.out.println("============================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + "| ");
        System.out.print(isPalindrome2(head) + "| ");
        System.out.println(isPalindrome3(head) + "| ");
        printLinkedList(head);
        System.out.println("============================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + "| ");
        System.out.print(isPalindrome2(head) + "| ");
        System.out.println(isPalindrome3(head) + "| ");
        printLinkedList(head);
        System.out.println("============================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + "| ");
        System.out.print(isPalindrome2(head) + "| ");
        System.out.println(isPalindrome3(head) + "| ");
        printLinkedList(head);
        System.out.println("============================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + "| ");
        System.out.print(isPalindrome2(head) + "| ");
        System.out.println(isPalindrome3(head) + "| ");
        printLinkedList(head);
        System.out.println("============================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + "| ");
        System.out.print(isPalindrome2(head) + "| ");
        System.out.println(isPalindrome3(head) + "| ");
        printLinkedList(head);
        System.out.println("============================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + "| ");
        System.out.print(isPalindrome2(head) + "| ");
        System.out.println(isPalindrome3(head) + "| ");
        printLinkedList(head);
        System.out.println("============================");
    }

}
