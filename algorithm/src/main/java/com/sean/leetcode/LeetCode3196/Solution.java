package com.sean.leetcode.LeetCode3196;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-04 17:20
 * @Description: https://leetcode.cn/problems/maximize-total-cost-of-alternating-subarrays
 * 3196. 最大化子数组的总成本
 * 给你一个长度为 n 的整数数组 nums。
 * 子数组 nums[l..r]（其中 0 <= l <= r < n）的 成本 定义为：
 * cost(l, r) = nums[l] - nums[l + 1] + ... + nums[r] * (−1)r − l
 * 你的任务是将 nums 分割成若干子数组，使得所有子数组的成本之和 最大化，并确保每个元素 正好 属于一个子数组。
 * 具体来说，如果 nums 被分割成 k 个子数组，且分割点为索引 i1, i2, ..., ik − 1（其中 0 <= i1 < i2 < ... < ik - 1 < n - 1），则总成本为：
 * cost(0, i1) + cost(i1 + 1, i2) + ... + cost(ik − 1 + 1, n − 1)
 * 返回在最优分割方式下的子数组成本之和的最大值。
 * 注意：如果 nums 没有被分割，即 k = 1，则总成本即为 cost(0, n - 1)。
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class Solution {

    public long maximumTotalCost(int[] nums) {
        int n = nums.length;
        long[] dp = new long[n + 1];
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i - 1], dp[i - 2] + nums[i - 2] - nums[i - 1]);
        }
        return dp[n];
    }

    public long maximumTotalCost1(int[] nums) {
        int n = nums.length;
        long f0 = 0, f1 = nums[0];
        for (int i = 2; i <= n; i++) {
            long t = f1;
            f1 = Math.max(f1 + nums[i - 1], f0 + nums[i - 2] - nums[i - 1]);
            f0 = t;
        }
        return f1;
    }

}
