package com.sean.leetcode;

import java.util.Stack;

/**
 * @Author xionghaiyang
 * @Date 2022/7/24 8:56
 */
public class LeetCode42 {

    /**
     * https://leetcode.cn/problems/trapping-rain-water/submissions/
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     */

    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int n = height.length, res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int num = stack.pop();
                while (!stack.isEmpty() && height[num] == height[stack.peek()]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    int a = i - stack.peek() - 1;
                    int h = Math.min(height[stack.peek()] - height[num], height[i] - height[num]);
                    res += a * h;
                }
            }
            stack.push(i);
        }
        return res;
    }

}
