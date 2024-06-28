package com.sean.leetcode.LeetCode1944;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-05 09:50
 * @Description: https://leetcode.cn/problems/number-of-visible-people-in-a-queue/
 * 1944. 队列中可以看到的人数
 * 有 n 个人排成一个队列，从左到右 编号为 0 到 n - 1 。
 * 给你以一个整数数组 heights ，每个整数 互不相同，heights[i] 表示第 i 个人的高度。
 * 一个人能 看到 他右边另一个人的条件是这两人之间的所有人都比他们两人 矮 。
 * 更正式的，第 i 个人能看到第 j 个人的条件是 i < j 且 min(heights[i], heights[j]) > max(heights[i+1], heights[i+2], ..., heights[j-1]) 。
 * 请你返回一个长度为 n 的数组 answer ，其中 answer[i] 是第 i 个人在他右侧队列中能 看到 的 人数 。
 */
public class Solution {

    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() < heights[i]) {
                stack.pop();
                res[i]++;
            }
            if (!stack.isEmpty()) {
                res[i]++;
            }
            stack.push(heights[i]);
        }
        return res;
    }

}
