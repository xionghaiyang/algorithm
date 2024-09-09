package com.sean.leetcode.LeetCode2181;

/**
 * @Author xionghaiyang
 * @Date 2024-09-09 09:10
 * @Description https://leetcode.cn/problems/merge-nodes-in-between-zeros/
 * 2181. 合并零之间的节点
 * 给你一个链表的头节点 head ，该链表包含由 0 分隔开的一连串整数。
 * 链表的 开端 和 末尾 的节点都满足 Node.val == 0 。
 * 对于每两个相邻的 0 ，请你将它们之间的所有节点合并成一个节点，其值是所有已合并节点的值之和。
 * 然后将所有 0 移除，修改后的链表不应该含有任何 0 。
 * 返回修改后链表的头节点 head 。
 */
public class Solution {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeNodes(ListNode head) {
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;
        int total = 0;
        for (ListNode cur = head.next; cur != null; cur = cur.next) {
            if (cur.val == 0) {
                ListNode node = new ListNode(total);
                tail.next = node;
                tail = tail.next;
                total = 0;
            } else {
                total += cur.val;
            }
        }
        return dummyHead.next;
    }

}
