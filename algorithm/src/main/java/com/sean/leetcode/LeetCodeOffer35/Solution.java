package com.sean.leetcode.LeetCodeOffer35;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-09 22:05
 * @Description: https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof/?plan=lcof&plan_progress=zq3t7ii
 * 剑指 Offer 35. 复杂链表的复制
 * 请实现 copyRandomList 函数，复制一个复杂链表。
 * 在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，
 * 还有一个 random 指针指向链表中的任意节点或者 null。
 */
public class Solution {

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        return process(head, map);
    }

    private Node process(Node head, Map<Node, Node> map) {
        if (head == null) {
            return null;
        }
        if (map.containsKey(head)) {
            return map.get(head);
        }
        Node res = new Node(head.val);
        map.put(head, res);
        res.next = process(head.next, map);
        res.random = process(head.random, map);
        return res;
    }

}
