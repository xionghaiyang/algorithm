package com.sean.leetcode.LeetCode2865;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-27 12:05
 * @Description: https://leetcode.cn/problems/beautiful-towers-i/description/
 * 2865. 美丽塔 I
 * 给你一个长度为 n 下标从 0 开始的整数数组 maxHeights 。
 * 你的任务是在坐标轴上建 n 座塔。
 * 第 i 座塔的下标为 i ，高度为 heights[i] 。
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
        long[] leftSums = new long[n];
        Deque<Integer> leftStack = new ArrayDeque<>();
        leftStack.push(-1);
        long leftSum = 0;
        for (int i = 0; i < n; i++) {
            int height = maxHeights.get(i);
            while (leftStack.size() > 1 && maxHeights.get(leftStack.peek()) > height) {
                int preIndex = leftStack.pop();
                int prevHeight = maxHeights.get(preIndex);
                leftSum -= (long) (preIndex - leftStack.peek()) * prevHeight;
            }
            leftSum += (long) (i - leftStack.peek()) * height;
            leftSums[i] = leftSum;
            leftStack.push(i);
        }
        long[] rightSums = new long[n];
        Deque<Integer> rightStack = new ArrayDeque<>();
        rightStack.push(n);
        long rightSum = 0;
        for (int i = n - 1; i >= 0; i--) {
            int height = maxHeights.get(i);
            while (rightStack.size() > 1 && maxHeights.get(rightStack.peek()) > height) {
                int prevIndex = rightStack.pop();
                int prevHeight = maxHeights.get(prevIndex);
                rightSum -= (long) (rightStack.peek() - prevIndex) * prevHeight;
            }
            rightSum += (long) (rightStack.peek() - i) * height;
            rightSums[i] = rightSum;
            rightStack.push(i);
        }
        long res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, leftSums[i] + rightSums[i] - maxHeights.get(i));
        }
        return res;
    }

}
