package com.sean.leetcode.LeetCodeInterview1721;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author xionghaiyang
 * @Date 2026-03-27 11:16
 * @Description https://leetcode.cn/problems/volume-of-histogram-lcci
 * 面试题 17.21. 直方图的水量
 * 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。
 */
public class Solution {

    public int trap(int[] height) {
        int res = 0;
        int n = height.length;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int pop = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int w = i - left - 1, h = Math.min(height[left], height[i]) - height[pop];
                res += w * h;
            }
            stack.push(i);
        }
        return res;
    }

}
