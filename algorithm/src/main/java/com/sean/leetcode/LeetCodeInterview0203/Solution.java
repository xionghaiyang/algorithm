package com.sean.leetcode.LeetCodeInterview0203;

/**
 * @Author xionghaiyang
 * @Date 2025-07-31 14:12
 * @Description https://leetcode.cn/problems/delete-middle-node-lcci
 * 面试题 02.03. 删除中间节点
 * 若链表中的某个节点，既不是链表头节点，也不是链表尾节点，则称其为该链表的「中间节点」。
 * 假定已知链表的某一个中间节点，请实现一种算法，将该节点从链表中删除。
 * 例如，传入节点 c（位于单向链表 a->b->c->d->e->f 中），将其删除后，剩余链表为 a->b->d->e->f
 */
public class Solution {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

}
