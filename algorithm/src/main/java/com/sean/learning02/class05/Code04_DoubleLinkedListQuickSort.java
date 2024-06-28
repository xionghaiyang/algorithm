package com.sean.learning02.class05;

import java.util.ArrayList;
import java.util.Comparator;

public class Code04_DoubleLinkedListQuickSort {

    public static class Node {
        public int value;
        public Node last;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node quickSort(Node h) {
        if (h == null) {
            return null;
        }
        int N = 0;
        Node c = h;
        Node e = null;
        while (c != null) {
            N++;
            e = c;
            c = c.next;
        }
        return process(h, e, N).h;
    }

    public static class HeadTail {
        public Node h;
        public Node t;

        public HeadTail(Node head, Node tail) {
            h = head;
            t = tail;
        }
    }

    public static HeadTail process(Node L, Node R, int N) {
        if (L == null) {
            return null;
        }
        if (L == R) {
            return new HeadTail(L, R);
        }
        int randomIndex = (int) (Math.random() * N);
        Node randomNode = L;
        while (randomIndex-- != 0) {
            randomNode = randomNode.next;
        }
        if (randomNode == L || randomNode == R) {
            if (randomNode == L) {
                L = randomNode.next;
                L.last = null;
            } else {
                randomNode.last.next = null;
            }
        } else {
            randomNode.last.next = randomNode.next;
            randomNode.next.last = randomNode.last;
        }
        randomNode.last = null;
        randomNode.next = null;
        Info info = partition(L, randomNode);
        HeadTail lht = process(info.lh, info.lt, info.ls);
        HeadTail rht = process(info.rh, info.rt, info.rs);
        if (lht != null) {
            lht.t.next = info.eh;
            info.eh.last = lht.t;
        }
        if (rht != null) {
            info.et.next = rht.h;
            rht.h.last = info.et;
        }
        Node h = lht != null ? lht.h : info.eh;
        Node t = rht != null ? rht.t : info.et;
        return new HeadTail(h, t);
    }

    public static class Info {
        public Node lh;
        public Node lt;
        public int ls;
        public Node rh;
        public Node rt;
        public int rs;
        public Node eh;
        public Node et;


        public Info(Node lh, Node lt, int ls, Node rh, Node rt, int rs, Node eh, Node et) {
            this.lh = lh;
            this.lt = lt;
            this.ls = ls;
            this.rh = rh;
            this.rt = rt;
            this.rs = rs;
            this.eh = eh;
            this.et = et;
        }
    }

    public static Info partition(Node L, Node pivot) {
        Node lh = null;
        Node lt = null;
        int ls = 0;
        Node rh = null;
        Node rt = null;
        int rs = 0;
        Node eh = pivot;
        Node et = pivot;
        Node tmp = null;
        while (L != null) {
            tmp = L.next;
            L.next = null;
            L.last = null;
            if (L.value < pivot.value) {
                ls++;
                if (lh == null) {
                    lh = L;
                    lt = L;
                } else {
                    lt.next = L;
                    L.last = lt;
                    lt = L;
                }
            } else if (L.value > pivot.value) {
                rs++;
                if (rh == null) {
                    rh = L;
                    rt = L;
                } else {
                    rt.next = L;
                    L.last = rt;
                    rt = L;
                }
            } else {
                et.next = L;
                L.last = et;
                et = L;
            }
            L = tmp;
        }
        return new Info(lh, lt, ls, rh, rt, rs, eh, et);
    }

    public static Node generateRandomDoubleLinkedList(int n, int v) {
        if (n == 0) {
            return null;
        }
        Node[] arr = new Node[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Node((int) (Math.random() * v));
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

    public static Node cloneDoubleLinkedList(Node head) {
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

    public static Node sort(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        while (head != null) {
            arr.add(head);
            head = head.next;
        }
        arr.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.value - o2.value;
            }
        });
        Node h = arr.get(0);
        h.last = null;
        Node p = h;
        for (int i = 1; i < arr.size(); i++) {
            Node c = arr.get(i);
            p.next = c;
            c.last = p;
            c.next = null;
            p = c;
        }
        return h;
    }

    public static boolean equal(Node h1, Node h2) {
        return doubleLinkedListToString(h1).equals(doubleLinkedListToString(h2));
    }

    public static String doubleLinkedListToString(Node head) {
        Node cur = head;
        Node end = null;
        StringBuilder builder = new StringBuilder();
        while (cur != null) {
            builder.append(cur.value + " ");
            end = cur;
            cur = cur.next;
        }
        builder.append("| ");
        while (end != null) {
            builder.append(end.value + " ");
            end = end.last;
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        int N = 500;
        int V = 500;
        int testTime = 100000;
        System.out.println("test begin!");
        for (int i = 0; i < testTime; i++) {
            int size = (int) (Math.random() * N);
            Node head1 = generateRandomDoubleLinkedList(size, V);
            Node head2 = cloneDoubleLinkedList(head1);
            Node sort1 = quickSort(head1);
            Node sort2 = sort(head2);
            if (!equal(sort1, sort2)) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("test end!");
    }

}
