package com.sean.leetcode.LeetCode3510;

import java.util.PriorityQueue;

/**
 * @Author xionghaiyang
 * @Date 2026-01-23 09:30
 * @Description https://leetcode.cn/problems/minimum-pair-removal-to-sort-array-ii
 * 3510. 移除最小数对使数组有序 II
 * 给你一个数组 nums，你可以执行以下操作任意次数：
 * 选择 相邻 元素对中 和最小 的一对。
 * 如果存在多个这样的对，选择最左边的一个。
 * 用它们的和替换这对元素。
 * 返回将数组变为 非递减 所需的 最小操作次数 。
 * 如果一个数组中每个元素都大于或等于它前一个元素（如果存在的话），则称该数组为非递减。
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class Solution {

    public class Node {
        long value;
        int left;
        Node prev;
        Node next;

        public Node(long value, int left) {
            this.value = value;
            this.left = left;
        }
    }

    public class PQItem implements Comparable<PQItem> {
        Node first;
        Node second;
        long cost;

        public PQItem(Node first, Node second, long cost) {
            this.first = first;
            this.second = second;
            this.cost = cost;
        }

        @Override
        public int compareTo(PQItem that) {
            if (this.cost == that.cost) {
                return this.first.left - that.first.left;
            }
            return this.cost < that.cost ? -1 : 1;
        }
    }

    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        PriorityQueue<PQItem> heap = new PriorityQueue<>();
        boolean[] merged = new boolean[n];
        int decreaseCount = 0;
        int count = 0;
        Node head = new Node(nums[0], 0);
        Node cur = head;
        for (int i = 1; i < n; i++) {
            Node newNode = new Node(nums[i], i);
            cur.next = newNode;
            newNode.prev = cur;
            heap.offer(new PQItem(cur, newNode, cur.value + newNode.value));
            if (nums[i - 1] > nums[i]) {
                decreaseCount++;
            }
            cur = newNode;
        }
        while (decreaseCount > 0) {
            PQItem item = heap.poll();
            Node first = item.first;
            Node second = item.second;
            long cost = item.cost;
            if (merged[first.left] || merged[second.left] || first.value + second.value != cost) {
                continue;
            }
            count++;
            if (first.value > second.value) {
                decreaseCount--;
            }
            Node prevNode = first.prev;
            Node nextNode = second.next;
            first.next = nextNode;
            if (nextNode != null) {
                nextNode.prev = first;
            }
            if (prevNode != null) {
                if (prevNode.value > first.value && prevNode.value <= cost) {
                    decreaseCount--;
                } else if (prevNode.value <= first.value && prevNode.value > cost) {
                    decreaseCount++;
                }
                heap.offer(new PQItem(prevNode, first, prevNode.value + cost));
            }
            if (nextNode != null) {
                if (second.value > nextNode.value && cost <= nextNode.value) {
                    decreaseCount--;
                } else if (second.value <= nextNode.value && cost > nextNode.value) {
                    decreaseCount++;
                }
                heap.offer(new PQItem(first, nextNode, cost + nextNode.value));
            }
            first.value = cost;
            merged[second.left] = true;
        }
        return count;
    }

}
