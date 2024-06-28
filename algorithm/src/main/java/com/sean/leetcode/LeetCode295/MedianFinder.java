package com.sean.leetcode.LeetCode295;

import java.util.PriorityQueue;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-16 17:43
 * @Description: https://leetcode.cn/problems/find-median-from-data-stream/description/
 * 295. 数据流的中位数
 * 中位数是有序整数列表中的中间值。
 * 如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
 * 例如 arr = [2,3,4] 的中位数是 3 。
 * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
 * 实现 MedianFinder 类:
 * MedianFinder() 初始化 MedianFinder 对象。
 * void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
 * double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。
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
