package com.sean.nowcoder;

import java.util.ArrayList;

public class NowCoderBM5 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 != null ? list1 : list2;
        return dummyHead.next;
    }

    public static ListNode divmerge(ArrayList<ListNode> lists, int left, int right) {
        if (left > right) {
            return null;
        }
        if (left == right) {
            return lists.get(left);
        }
        int mid = left + ((right - left) >> 1);
        return merge(divmerge(lists, left, mid), divmerge(lists, mid + 1, right));
    }

    public static ListNode mergeKLists(ArrayList<ListNode> lists) {
        return divmerge(lists, 0, lists.size() - 1);
    }

}
