package com.sean.nowcoder;

public class NowCoderBM10 {

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static class Info {
        int length;
        ListNode lastListNode;

        public Info(int length, ListNode lastListNode) {
            this.length = length;
            this.lastListNode = lastListNode;
        }
    }

    public static Info listInfo(ListNode listNode) {
        ListNode cur = listNode;
        int length = 0;
        while (cur != null) {
            cur = cur.next;
            length++;
        }
        return new Info(length, cur);
    }

    public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        Info info1 = listInfo(pHead1);
        Info info2 = listInfo(pHead2);
        if (info1.lastListNode != info2.lastListNode) {
            return null;
        }
        ListNode cur1 = info1.length > info2.length ? pHead1 : pHead2;
        ListNode cur2 = cur1 == pHead1 ? pHead2 : pHead1;
        for (int i = 0; i < Math.abs(info1.length - info2.length); i++) {
            cur1 = cur1.next;
        }
        while (cur1 != null && cur2 != null && cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

}
