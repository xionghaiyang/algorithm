package com.sean.leetcode.LeetCode1696;

import java.util.LinkedList;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-02-05 15:05
 * @Description: https://leetcode.cn/problems/jump-game-vi/
 * 1696. 跳跃游戏 VI
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
 * 一开始你在下标 0 处。每一步，你最多可以往前跳 k 步，但你不能跳出数组的边界。
 * 也就是说，你可以从下标 i 跳到 [i + 1， min(n - 1, i + k)] 包含 两个端点的任意位置。
 * 你的目标是到达数组最后一个位置（下标为 n - 1 ），你的 得分 为经过的所有数字之和。
 * 请你返回你能得到的 最大得分 。
 */
public class Solution {

    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        boolean[] visited = new boolean[n];
        return process(nums, k, dp, visited, 0);
    }

    private int process(int[] nums, int k, int[] dp, boolean[] visited, int i) {
        int n = nums.length;
        if (visited[i]) {
            return dp[i];
        }
        int dpj = Integer.MIN_VALUE;
        for (int j = i + 1; j <= Math.min(n - 1, i + k); j++) {
            dpj = Math.max(dpj, process(nums, k, dp, visited, j));
        }
        int res = nums[i] + (dpj == Integer.MIN_VALUE ? 0 : dpj);
        visited[i] = true;
        return dp[i] = res;
    }

    public int maxResult1(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.offerLast(0);
        for (int i = 1; i < n; i++) {
            while (linkedList.peekFirst() < i - k) {
                linkedList.pollFirst();
            }
            dp[i] = dp[linkedList.peekFirst()] + nums[i];
            while (!linkedList.isEmpty() && dp[linkedList.peekLast()] <= dp[i]) {
                linkedList.pollLast();
            }
            linkedList.offerLast(i);
        }
        return dp[n - 1];
    }

}
