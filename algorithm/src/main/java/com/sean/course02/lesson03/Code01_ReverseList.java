package com.sean.course02.lesson03;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-03-28 20:41
 * @Description 单链表和双链表如何反转
 */
public class Code01_ReverseList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int value) {
            this.value = value;
        }
    }

    //head
    //a -> b -> c -> null
    //c -> b -> a -> null
    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    private static Node testReverseLinkedList(Node head) {
        if (head == null) {
            return null;
        }
        List<Node> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.get(0).next = null;
        int n = list.size();
        for (int i = 1; i < n; i++) {
            list.get(i).next = list.get(i - 1);
        }
        return list.get(n - 1);
    }

    private static DoubleNode testReverseDoubleList(DoubleNode head) {
        if (head == null) {
            return null;
        }
        List<DoubleNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.get(0).next = null;
        DoubleNode pre = list.get(0);
        int n = list.size();
        for (int i = 1; i < n; i++) {
            DoubleNode cur = list.get(i);
            cur.last = null;
            cur.next = pre;
            pre.last = cur;
            pre = cur;
        }
        return list.get(n - 1);
    }

    private static Node generateRandomLinkedList(int len, int value) {
        int size = (int) ((len + 1) * Math.random());
        if (size == 0) {
            return null;
        }
        size--;
        Node head = new Node((int) ((value + 1) * Math.random()));
        Node pre = head;
        while (size != 0) {
            Node cur = new Node((int) ((value + 1) * Math.random()));
            pre.next = cur;
            pre = cur;
            size--;
        }
        return head;
    }

    private static DoubleNode generateRandomDoubleList(int len, int value) {
        int size = (int) ((len + 1) * Math.random());
        if (size == 0) {
            return null;
        }
        size--;
        DoubleNode head = new DoubleNode((int) ((value + 1) * Math.random()));
        DoubleNode pre = head;
        while (size != 0) {
            DoubleNode cur = new DoubleNode((int) ((value + 1) * Math.random()));
            pre.next = cur;
            cur.last = pre;
            pre = cur;
            size--;
        }
        return head;
    }

    private static List<Integer> getLinkedListOriginOrder(Node head) {
        List<Integer> res = new ArrayList<>();
        while (head != null) {
            res.add(head.value);
            head = head.next;
        }
        return res;
    }

    private static boolean checkLinkedListReverse(List<Integer> origin, Node head) {
        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.value)) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    private static List<Integer> getDoubleListOriginOrder(DoubleNode head) {
        List<Integer> res = new ArrayList<>();
        while (head != null) {
            res.add(head.value);
            head = head.next;
        }
        return res;
    }

    private static boolean checkDoubleListReverse(List<Integer> origin, DoubleNode head) {
        DoubleNode end = null;
        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.value)) {
                return false;
            }
            end = head;
            head = head.next;
        }
        for (int i = 0; i < origin.size(); i++) {
            if (!origin.get(i).equals(end.value)) {
                return false;
            }
            end = end.last;
        }
        return true;
    }

    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 100000;
        System.out.println("测试开始!");
        for (int i = 0; i < testTime; i++) {
            Node node1 = generateRandomLinkedList(len, value);
            List<Integer> list1 = getLinkedListOriginOrder(node1);
            node1 = reverseLinkedList(node1);
            if (!checkLinkedListReverse(list1, node1)) {
                System.out.println("出错了1!");
            }

            Node node2 = generateRandomLinkedList(len, value);
            List<Integer> list2 = getLinkedListOriginOrder(node2);
            node2 = testReverseLinkedList(node2);
            if (!checkLinkedListReverse(list2, node2)) {
                System.out.println("出错了2!");
            }

            DoubleNode node3 = generateRandomDoubleList(len, value);
            List<Integer> list3 = getDoubleListOriginOrder(node3);
            node3 = reverseDoubleList(node3);
            if (!checkDoubleListReverse(list3, node3)) {
                System.out.println("出错了3!");
            }

            DoubleNode node4 = generateRandomDoubleList(len, value);
            List<Integer> list4 = getDoubleListOriginOrder(node4);
            node4 = reverseDoubleList(node4);
            if (!checkDoubleListReverse(list4, node4)) {
                System.out.println("出错了4");
            }
        }
        System.out.println("测试结束!");
    }

}
