package com.sean.base.chapter09;

import java.util.Stack;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-24 20:33
 * @Description: 给定一个单链表的头节点head，请判断该链表是否为回文结构。
 */
public class Code02_IsPalindromeList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }

    }

    //need n extra space
    public boolean isPalindrome1(Node head) {
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

    //need n/2 extra space
    public boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node slow = head.next;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Stack<Node> stack = new Stack<>();
        while (slow != null) {
            stack.push(slow);
            slow = slow.next;
        }
        while (!stack.isEmpty()) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public boolean isPalindrome3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node node1 = head;
        Node node2 = head;
        while (node2.next != null && node2.next.next != null) {
            node1 = node1.next;  //node1 -> mid
            node2 = node2.next.next; //node2 -> end
        }
        node2 = node1.next; //node2 -> right part first node
        node1.next = null; //mid.next -> null
        Node node3 = null;
        while (node2 != null) {
            node3 = node2.next; //node3 -> save next node
            node2.next = node1; //
            node1 = node2;
            node2 = node3;
        }
        node3 = node1; // node3 -> save first node
        node2 = head; // node2 -> left first node
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

    public void printLinkedList(Node node) {
        System.out.print("Linked List:");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Code02_IsPalindromeList solution = new Code02_IsPalindromeList();
        Node head = null;
        solution.printLinkedList(head);
        System.out.print(solution.isPalindrome1(head) + " | ");
        System.out.print(solution.isPalindrome2(head) + " | ");
        System.out.println(solution.isPalindrome3(head) + " | ");
        solution.printLinkedList(head);
        System.out.println("==========================");
        head = new Node(1);
        solution.printLinkedList(head);
        System.out.print(solution.isPalindrome1(head) + " | ");
        System.out.print(solution.isPalindrome2(head) + " | ");
        System.out.println(solution.isPalindrome3(head) + " | ");
        solution.printLinkedList(head);
        System.out.println("==========================");
        head = new Node(1);
        head.next = new Node(2);
        solution.printLinkedList(head);
        System.out.print(solution.isPalindrome1(head) + " | ");
        System.out.print(solution.isPalindrome2(head) + " | ");
        System.out.println(solution.isPalindrome3(head) + " | ");
        solution.printLinkedList(head);
        System.out.println("==========================");
        head = new Node(1);
        head.next = new Node(1);
        solution.printLinkedList(head);
        System.out.print(solution.isPalindrome1(head) + " | ");
        System.out.print(solution.isPalindrome2(head) + " | ");
        System.out.println(solution.isPalindrome3(head) + " | ");
        solution.printLinkedList(head);
        System.out.println("==========================");
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        solution.printLinkedList(head);
        System.out.print(solution.isPalindrome1(head) + " | ");
        System.out.print(solution.isPalindrome2(head) + " | ");
        System.out.println(solution.isPalindrome3(head) + " | ");
        solution.printLinkedList(head);
        System.out.println("==========================");
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        solution.printLinkedList(head);
        System.out.print(solution.isPalindrome1(head) + " | ");
        System.out.print(solution.isPalindrome2(head) + " | ");
        System.out.println(solution.isPalindrome3(head) + " | ");
        solution.printLinkedList(head);
        System.out.println("==========================");
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        solution.printLinkedList(head);
        System.out.print(solution.isPalindrome1(head) + " | ");
        System.out.print(solution.isPalindrome2(head) + " | ");
        System.out.println(solution.isPalindrome3(head) + " | ");
        solution.printLinkedList(head);
        System.out.println("==========================");
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        solution.printLinkedList(head);
        System.out.print(solution.isPalindrome1(head) + " | ");
        System.out.print(solution.isPalindrome2(head) + " | ");
        System.out.println(solution.isPalindrome3(head) + " | ");
        solution.printLinkedList(head);
        System.out.println("==========================");
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        solution.printLinkedList(head);
        System.out.print(solution.isPalindrome1(head) + " | ");
        System.out.print(solution.isPalindrome2(head) + " | ");
        System.out.println(solution.isPalindrome3(head) + " | ");
        solution.printLinkedList(head);
    }

}
