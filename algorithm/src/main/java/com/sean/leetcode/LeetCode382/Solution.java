package com.sean.leetcode.LeetCode382;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-03 14:00
 * @Description: https://leetcode.cn/problems/linked-list-random-node/description/
 * 382. 链表随机节点
 * 给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。
 * 每个节点 被选中的概率一样 。
 * 实现 Solution 类：
 * Solution(ListNode head) 使用整数数组初始化对象。
 * int getRandom() 从链表中随机选择一个节点并返回该节点的值。
 * 链表中所有节点被选中的概率相等。
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

//    List<Integer> list;
//    Random random;
//
//    public Solution(ListNode head) {
//        list = new ArrayList<>();
//        while (head != null) {
//            list.add(head.val);
//            head = head.next;
//        }
//        random = new Random();
//    }
//
//    public int getRandom() {
//        return list.get(random.nextInt(list.size()));
//    }

    ListNode head;
    Random random;

    public Solution(ListNode head) {
        this.head = head;
        random = new Random();
    }

    public int getRandom() {
        int i = 1, res = 0;
        for (ListNode node = head; node != null; node = node.next) {
            if (random.nextInt(i) == 0) {
                res = node.val;
            }
            i++;
        }
        return res;
    }

}
