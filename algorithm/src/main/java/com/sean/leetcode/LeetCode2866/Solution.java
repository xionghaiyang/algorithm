package com.sean.leetcode.LeetCode2866;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-21 15:02
 * @Description: https://leetcode.cn/problems/beautiful-towers-ii/
 * 2866. 美丽塔 II
 * 给你一个长度为 n 下标从 0 开始的整数数组 maxHeights 。
 * 你的任务是在坐标轴上建 n 座塔。第 i 座塔的下标为 i ，高度为 heights[i] 。
 * 如果以下条件满足，我们称这些塔是 美丽 的：
 * 1 <= heights[i] <= maxHeights[i]
 * heights 是一个 山脉 数组。
 * 如果存在下标 i 满足以下条件，那么我们称数组 heights 是一个 山脉 数组：
 * 对于所有 0 < j <= i ，都有 heights[j - 1] <= heights[j]
 * 对于所有 i <= k < n - 1 ，都有 heights[k + 1] <= heights[k]
 * 请你返回满足 美丽塔 要求的方案中，高度和的最大值 。
 */
public class Solution {

    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        long res = 0;
        long[] prefix = new long[n];
        long[] suffix = new long[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && maxHeights.get(i) < maxHeights.get(stack.peek())) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                prefix[i] = (long) (i + 1) * maxHeights.get(i);
            } else {
                prefix[i] = prefix[stack.peek()] + (long) (i - stack.peek()) * maxHeights.get(i);
            }
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && maxHeights.get(i) < maxHeights.get(stack.peek())) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                suffix[i] = (long) (n - i) * maxHeights.get(i);
            } else {
                suffix[i] = suffix[stack.peek()] + (long) (stack.peek() - i) * maxHeights.get(i);
            }
            stack.push(i);
            res = Math.max(res, prefix[i] + suffix[i] - maxHeights.get(i));
        }
        return res;
    }

}
