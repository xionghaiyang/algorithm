package com.sean.leetcode;

import java.util.HashMap;

/**
 * 复制带随机指针的链表
 * https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 */
public class LeetCode138 {

    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    private HashMap<Node, Node> hashMap = new HashMap<Node, Node>();

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        if (this.hashMap.containsKey(head)) {
            return this.hashMap.get(head);
        }
        Node node = new Node(head.val);
        this.hashMap.put(head, node);
        node.next = copyRandomList(head.next);
        node.random = copyRandomList(head.random);
        return node;
    }
}
