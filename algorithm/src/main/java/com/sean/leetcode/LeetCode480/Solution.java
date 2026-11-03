package com.sean.leetcode.LeetCode480;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Author: xionghaiyang
 * @Date: 2023-12-16 18:06
 * @Description: https://leetcode.cn/problems/sliding-window-median
 * 480. 滑动窗口中位数
 * 中位数是有序序列最中间的那个数。
 * 如果序列的长度是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
 * 例如：
 * [2,3,4]，中位数是 3
 * [2,3]，中位数是 (2 + 3) / 2 = 2.5
 * 给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。
 * 窗口中有 k 个数，每次窗口向右移动 1 位。
 * 你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 * 你可以假设 k 始终有效，即：k 始终小于等于输入的非空数组的元素个数。
 * 与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。
 */
public class Solution {

    public class LazyHeap extends PriorityQueue<Integer> {
        //每个元素剩余需要删除的次数
        private final Map<Integer, Integer> removeCnt = new HashMap<>();
        //实际大小
        private int size = 0;

        public LazyHeap(Comparator<Integer> comparator) {
            super(comparator);
        }

        public int size() {
            return size;
        }

        //删除
        public void remove(int x) {
            removeCnt.merge(x, 1, Integer::sum);
            size--;
        }

        //正式执行删除操作
        private void applyRemove() {
            while (removeCnt.getOrDefault(peek(), 0) > 0) {
                removeCnt.merge(poll(), -1, Integer::sum);
            }
        }

        //查看堆顶
        public int top() {
            applyRemove();
            return peek();
        }

        //出堆
        public int pop() {
            applyRemove();
            size--;
            return poll();
        }

        //入堆
        public void push(int x) {
            offer(x);
            size++;
        }

        //push(x)然后pop()
        public int pushPop(int x) {
            //可以替换堆顶
            if (size > 0 && comparator().compare(x, top()) > 0) {
                offer(x);
                return poll();
            }
            return x;
        }
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] res = new double[n - k + 1];
        //最大堆
        LazyHeap left = new LazyHeap((a, b) -> Integer.compare(b, a));
        //最小堆
        LazyHeap right = new LazyHeap(Integer::compare);
        for (int i = 0; i < n; i++) {
            int in = nums[i];
            if (left.size() == right.size()) {
                left.push(right.pushPop(in));
            } else {
                right.push(left.pushPop(in));
            }
            int l = i + 1 - k;
            if (l < 0) {
                continue;
            }
            if ((k & 1) > 0) {
                res[l] = left.top();
            } else {
                res[l] = ((long) left.top() + right.top()) / 2.0;
            }
            int out = nums[l];
            if (out <= left.top()) {
                left.remove(out);
                if (left.size() < right.size()) {
                    left.push(right.pop());
                }
            } else {
                right.remove(out);
                if (left.size() > right.size() + 1) {
                    right.push(left.pop());
                }
            }
        }
        return res;
    }

}
