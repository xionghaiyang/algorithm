package com.sean.course02.lesson10;

/**
 * @Author xionghaiyang
 * @Date 2025-05-08 11:26
 * @Description 给定两个可能有环也可能无环的单链表，头节点head1和head2。
 * 请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返回null
 * 要求:如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)。
 */
public class Code01_FindFirstIntersectNode {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loopNode1 = getLoopNode(head1);
        Node loopNode2 = getLoopNode(head2);
        if (loopNode1 == null && loopNode2 == null) {
            return noLoop(head1, head2);
        }
        if (loopNode1 != null && loopNode2 != null) {
            return bothLoop(head1, loopNode1, head2, loopNode2);
        }
        return null;
    }

    //找到链表第一个入环节点，如果无环，返回null
    private static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            slow = slow.next;
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //如果两个链表都无环，返回第一个相交节点，如果不相交，返回null
    private static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null;
        }
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    //两个有环链表，返回第一个相交节点，如果不相交返回null
    private static Node bothLoop(Node head1, Node loopNode1, Node head2, Node loopNode2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loopNode1 == loopNode2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loopNode1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loopNode2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            cur1 = loopNode1.next;
            while (cur1 != loopNode1) {
                if (cur1 == loopNode2) {
                    return loopNode1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        //1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        //0 -> 9 -> 8 -> 6 -> 7 -> null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next;
        System.out.println(getIntersectNode(head1, head2).value);

        //1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 4 ...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = head1.next.next.next;

        //0 -> 9 -> 8 -> 2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next;
        System.out.println(getIntersectNode(head1, head2).value);

        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next;
        System.out.println(getIntersectNode(head1, head2).value);
    }

}
