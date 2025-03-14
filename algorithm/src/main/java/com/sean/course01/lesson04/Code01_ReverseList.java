package com.sean.course01.lesson04;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-03-14 15:47
 * @Description 单双链表的反转
 */
public class Code01_ReverseList {

    public static class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static class DoubleNode {
        public int val;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int val) {
            this.val = val;
        }
    }

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

    private static Node generateRandomLinkedList(int len, int value) {
        int size = (int) ((len + 1) * Math.random());
        if (size == 0) {
            return null;
        }
        Node head = new Node((int) ((value + 1) * Math.random()));
        size--;
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
        DoubleNode head = new DoubleNode((int) ((value + 1) * Math.random()));
        size--;
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
            res.add(head.val);
            head = head.next;
        }
        return res;
    }

    private static boolean checkLinkedListReverse(List<Integer> origin, Node head) {
        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.val)) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    private static List<Integer> getDoubleListOriginOrder(DoubleNode head) {
        List<Integer> res = new ArrayList<>();
        while (head != null) {
            res.add(head.val);
            head = head.next;
        }
        return res;
    }

    private static boolean checkDoubleListReverse(List<Integer> origin, DoubleNode head) {
        DoubleNode end = null;
        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.val)) {
                return false;
            }
            end = head;
            head = head.next;
        }
        for (int i = 0; i < origin.size(); i++) {
            if (!origin.get(i).equals(end.val)) {
                return false;
            }
            end = end.last;
        }
        return true;
    }

    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            Node node1 = generateRandomLinkedList(len, value);
            List<Integer> list1 = getLinkedListOriginOrder(node1);
            node1 = reverseLinkedList(node1);
            if (!checkLinkedListReverse(list1, node1)) {
                System.out.println("出错了1");
            }

            DoubleNode node2 = generateRandomDoubleList(len, value);
            List<Integer> list2 = getDoubleListOriginOrder(node2);
            node2 = reverseDoubleList(node2);
            if (!checkDoubleListReverse(list2, node2)) {
                System.out.println("出错了2");
            }
        }
        System.out.println("测试结束");
    }

}
