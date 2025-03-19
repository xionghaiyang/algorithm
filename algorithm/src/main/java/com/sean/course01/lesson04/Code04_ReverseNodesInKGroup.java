package com.sean.course01.lesson04;

/**
 * @Author xionghaiyang
 * @Date 2025-03-19 21:05
 * @Description https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 * K 个一组翻转链表
 */
public class Code04_ReverseNodesInKGroup {

    class ListNode {
        public int val;
        public ListNode next;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        ListNode end = getKGroupEnd(start, k);
        if (end == null) {
            return head;
        }
        //第一组凑齐了
        head = end;
        reverse(start, end);
        //上一组的结尾节点
        ListNode lastEnd = start;
        while (lastEnd.next != null) {
            start = lastEnd.next;
            end = getKGroupEnd(start, k);
            if (end == null) {
                return head;
            }
            reverse(start, end);
            lastEnd.next = end;
            lastEnd = start;
        }
        return head;
    }

    private ListNode getKGroupEnd(ListNode start, int k) {
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }

    private void reverse(ListNode start, ListNode end) {
        end = end.next;
        ListNode pre = null;
        ListNode cur = start;
        ListNode next = null;
        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;
    }

}
