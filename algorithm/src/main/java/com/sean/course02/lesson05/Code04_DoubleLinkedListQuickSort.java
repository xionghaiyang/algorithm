package com.sean.course02.lesson05;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-04-22 11:55
 * @Description 双向链表的随机快速排序
 */
public class Code04_DoubleLinkedListQuickSort {

    public static class Node {
        public int value;
        public Node last;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node quickSort(Node head) {
        if (head == null) {
            return null;
        }
        int n = 0;
        Node cur = head;
        Node tail = null;
        while (cur != null) {
            n++;
            tail = cur;
            cur = cur.next;
        }
        return process(head, tail, n).head;
    }

    public static class HeadTail {
        public Node head;
        public Node tail;

        public HeadTail(Node head, Node tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    //left...right是一个双向链表的头和尾
    //left的last指针指向null,right的next指针指向null
    //一共有n个节点
    //将这一段用随机快排的方式排好序
    //返回排好序之后的双向链表的头和尾
    private static HeadTail process(Node left, Node right, int n) {
        if (left == null) {
            return null;
        }
        if (left == right) {
            return new HeadTail(left, right);
        }
        int randomIndex = (int) (n * Math.random());
        Node randomNode = left;
        while (randomIndex-- != 0) {
            randomNode = randomNode.next;
        }
        if (randomNode == left || randomNode == right) {
            if (randomNode == left) {
                left = randomNode.next;
                left.last = null;
            } else {
                randomNode.last.next = null;
            }
        } else {
            randomNode.last.next = randomNode.next;
            randomNode.next.last = randomNode.last;
        }
        randomNode.last = null;
        randomNode.next = null;
        Info info = partition(left, randomNode);
        HeadTail lht = process(info.lH, info.lT, info.lS);
        HeadTail rht = process(info.rH, info.rT, info.rS);
        if (lht != null) {
            lht.tail.next = info.eH;
            info.eH.last = lht.tail;
        }
        if (rht != null) {
            info.eT.next = rht.head;
            rht.head.last = info.eT;
        }
        Node head = lht != null ? lht.head : info.eH;
        Node tail = rht != null ? rht.tail : info.eT;
        return new HeadTail(head, tail);
    }

    public static class Info {
        public Node lH;
        public Node lT;
        public int lS;
        public Node eH;
        public Node eT;
        public Node rH;
        public Node rT;
        public int rS;

        public Info(Node lH, Node lT, int lS, Node eH, Node eT, Node rH, Node rT, int rS) {
            this.lH = lH;
            this.lT = lT;
            this.lS = lS;
            this.eH = eH;
            this.eT = eT;
            this.rH = rH;
            this.rT = rT;
            this.rS = rS;
        }
    }

    //(left...一直到空)，是一个双向链表
    //pivot是一个不在(left...一直到空)的独立节点，它作为划分值
    //返回Info
    // 小于部分的头、尾、节点个数:lH,lT,lS
    //等于部分的头、尾:eH,eT
    //大于部分的头、尾、节点个数:rH,rT,rS
    private static Info partition(Node left, Node pivot) {
        Node lH = null;
        Node lT = null;
        int lS = 0;
        Node eH = pivot;
        Node eT = pivot;
        Node rH = null;
        Node rT = null;
        int rS = 0;
        Node tmp = null;
        while (left != null) {
            tmp = left.next;
            left.next = null;
            left.last = null;
            if (left.value < pivot.value) {
                lS++;
                if (lH == null) {
                    lH = left;
                    lT = left;
                } else {
                    lT.next = left;
                    left.last = lT;
                    lT = left;
                }
            } else if (left.value > pivot.value) {
                rS++;
                if (rH == null) {
                    rH = left;
                    rT = left;
                } else {
                    rT.next = left;
                    left.last = rT;
                    rT = left;
                }
            } else {
                eT.next = left;
                left.last = eT;
                eT = left;
            }
            left = tmp;
        }
        return new Info(lH, lT, lS, eH, eT, rH, rT, rS);
    }

    public static Node sort(Node head) {
        if (head == null) {
            return null;
        }
        List<Node> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        //Collections.sort(list, (a, b) -> a.value - b.value);
        list.sort((a, b) -> a.value - b.value);
        Node h = list.get(0);
        h.last = null;
        Node p = h;
        for (int i = 1; i < list.size(); i++) {
            Node c = list.get(i);
            p.next = c;
            c.last = p;
            c.next = null;
            p = c;
        }
        return h;
    }

    private static Node generateRandomDoubleLinkedList(int n, int v) {
        if (n == 0) {
            return null;
        }
        Node[] arr = new Node[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Node((int) (v * Math.random()));
        }
        Node head = arr[0];
        Node pre = head;
        for (int i = 1; i < n; i++) {
            pre.next = arr[i];
            arr[i].last = pre;
            pre = arr[i];
        }
        return head;
    }

    private static Node cloneDoubleLinkedList(Node head) {
        if (head == null) {
            return null;
        }
        Node h = new Node(head.value);
        Node p = h;
        head = head.next;
        while (head != null) {
            Node c = new Node(head.value);
            p.next = c;
            c.last = p;
            p = c;
            head = head.next;
        }
        return h;
    }

    private static boolean equal(Node h1, Node h2) {
        return doubleLinkedListToString(h1).equals(doubleLinkedListToString(h2));
    }

    private static String doubleLinkedListToString(Node head) {
        Node cur = head;
        Node end = null;
        StringBuilder res = new StringBuilder();
        while (cur != null) {
            res.append(cur.value + " ");
            end = cur;
            cur = cur.next;
        }
        res.append("| ");
        while (end != null) {
            res.append(end.value + " ");
            end = end.last;
        }
        return res.toString();
    }

    public static void main(String[] args) {
        int N = 500;
        int V = 500;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int size = (int) (N * Math.random());
            Node head1 = generateRandomDoubleLinkedList(size, V);
            Node head2 = cloneDoubleLinkedList(head1);
            Node sort1 = quickSort(head1);
            Node sort2 = sort(head2);
            if (!equal(sort1, sort2)) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
