package com.sean.leetcode.LeetCode1856;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-27 13:58
 * @Description: https://leetcode.cn/problems/maximum-subarray-min-product/description/
 * 1856. 子数组最小乘积的最大值
 * 一个数组的 最小乘积 定义为这个数组中 最小值 乘以 数组的 和 。
 * 比方说，数组 [3,2,5] （最小值是 2）的最小乘积为 2 * (3+2+5) = 2 * 10 = 20 。
 * 给你一个正整数数组 nums ，请你返回 nums 任意 非空子数组 的最小乘积 的 最大值 。
 * 由于答案可能很大，请你返回答案对  109 + 7 取余 的结果。
 * 请注意，最小乘积的最大值考虑的是取余操作 之前 的结果。
 * 题目保证最小乘积的最大值在 不取余 的情况下可以用 64 位有符号整数 保存。
 * 子数组 定义为一个数组的 连续 部分。
 */
public class Solution {

    int mod = (int) 1e9 + 7;

    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n - 1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                //right[i]是右侧最近的小于等于num[i]的元素下标
                right[stack.pop()] = i - 1;
            }
            if (!stack.isEmpty()) {
                //left[i]是左侧最近的严格小于num[i]的元素下标
                left[i] = stack.peek() + 1;
            }
            stack.push(i);
        }
        long[] pre = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }
        long best = 0;
        for (int i = 0; i < n; i++) {
            best = Math.max(best, (pre[right[i] + 1] - pre[left[i]]) * nums[i]);
        }
        return (int) (best % mod);
    }

}
