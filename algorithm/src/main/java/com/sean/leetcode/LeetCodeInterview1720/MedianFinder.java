package com.sean.leetcode.LeetCodeInterview1720;

import java.util.PriorityQueue;

/**
 * @Author xionghaiyang
 * @Date 2026-03-27 10:20
 * @Description https://leetcode.cn/problems/continuous-median-lcci
 * 面试题 17.20. 连续中值
 * 随机产生数字并传递给一个方法。
 * 你能否完成这个方法，在每次产生新值时，寻找当前所有值的中间值（中位数）并保存。
 * 中位数是有序列表中间的数。
 * 如果列表长度是偶数，中位数则是中间两个数的平均值。
 * 例如，
 * [2,3,4] 的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 */
public class MedianFinder {

    private PriorityQueue<Integer> left;
    private PriorityQueue<Integer> right;

    public MedianFinder() {
        left = new PriorityQueue<>((a, b) -> b - a);
        right = new PriorityQueue<>((a, b) -> a - b);
    }

    public void addNum(int num) {
        if (left.isEmpty() || num <= left.peek()) {
            left.offer(num);
            if (right.size() + 1 < left.size()) {
                right.offer(left.poll());
            }
        } else {
            right.offer(num);
            if (right.size() > left.size()) {
                left.offer(right.poll());
            }
        }
    }

    public double findMedian() {
        if (left.size() > right.size()) {
            return left.peek();
        }
        return (left.peek() + right.peek()) / 2.0;
    }

}
