package com.sean.leetcode.LeetCode907;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-28 08:29
 * @Description: https://leetcode.cn/problems/sum-of-subarray-minimums/
 * 907. 子数组的最小值之和
 * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 */
public class Solution {

    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                stack.pop();
            }
            left[i] = i - (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                stack.pop();
            }
            right[i] = (stack.isEmpty() ? n : stack.peek()) - i;
            stack.push(i);
        }
        long res = 0;
        int mod = 1000000007;
        for (int i = 0; i < n; i++) {
            res = (res + (long) left[i] * right[i] * arr[i]) % mod;
        }
        return (int) res;
    }

    public int sumSubarrayMins1(int[] arr) {
        int n = arr.length;
        long res = 0;
        int mod = 1000000007;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            int k = stack.isEmpty() ? (i + 1) : (i - stack.peek());
            dp[i] = k * arr[i] + (stack.isEmpty() ? 0 : dp[i - k]);
            res = (res + dp[i]) % mod;
            stack.push(i);
        }
        return (int) res;
    }

}
