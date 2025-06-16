package com.sean.leetcode.LeetCode84;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @Author xionghaiyang
 * @Date 2025-06-16 10:15
 * @Description https://leetcode.cn/problems/largest-rectangle-in-histogram
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。
 * 每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 1 <= heights.length <=10^5
 * 0 <= heights[i] <= 10^4
 */
public class Solution {

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        //left[i],heights中,i左侧第一个比heights[i]小的下标
        int[] left = new int[n];
        //right[i],heights中,i右侧第一个比heights[i]小的下标
        int[] right = new int[n];
        Arrays.fill(right, n);
        //小 -> 大
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                right[stack.pop()] = i;
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, (right[i] - left[i] - 1) * heights[i]);
        }
        return res;
    }

}
