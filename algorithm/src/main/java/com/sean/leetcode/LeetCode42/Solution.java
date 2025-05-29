package com.sean.leetcode.LeetCode42;

import java.util.Stack;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-27 17:13
 * @Description: https://leetcode.cn/problems/trapping-rain-water
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * n == height.length
 * 1 <= n <= 2 * 10^4
 * 0 <= height[i] <= 10
 */
public class Solution {

    public int trap(int[] height) {
        int n = height.length;
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int num = stack.pop();
                if (!stack.isEmpty()) {
                    int w = i - stack.peek() - 1;
                    int h = Math.min(height[stack.peek()], height[i]) - height[num];
                    res += w * h;
                }
            }
            stack.push(i);
        }
        return res;
    }

}
