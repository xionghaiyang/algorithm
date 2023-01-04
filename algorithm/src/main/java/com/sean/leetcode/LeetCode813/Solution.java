package com.sean.leetcode.LeetCode813;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-28 08:37
 * @Description: https://leetcode.cn/problems/largest-sum-of-averages/
 * 813. 最大平均值和的分组
 * 给定数组 nums 和一个整数 k 。
 * 我们将给定的数组 nums 分成 最多 k 个相邻的非空子数组 。 
 * 分数 由每个子数组内的平均值的总和构成。
 * 注意我们必须使用 nums 数组中的每一个数进行分组，并且分数不一定需要是整数。
 * 返回我们所能得到的最大 分数 是多少。答案误差在 10-6 内被视为是正确的。
 */
public class Solution {

    public double largestSumOfAverages1(int[] nums, int k) {
        int n = nums.length;
        long[] preSum = new long[n];
        preSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        return process(preSum, 0, 0, k);
    }

    private double process(long[] preSum, int left, int right, int rest) {
        int n = preSum.length;
        if (rest == 1 || right == n - 1) {
            return avg(preSum, left, n - 1);
        }
        return Math.max(avg(preSum, left, right) + process(preSum, right + 1, right + 1, rest - 1), process(preSum, left, right + 1, rest));
    }

    private double avg(long[] preSum, int left, int right) {
        long sum = preSum[right] - (left - 1 >= 0 ? preSum[left - 1] : 0);
        int cnt = right - left + 1;
        return (double) sum / cnt;
    }

    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        long[] preSum = new long[n];
        preSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        double[][][] dp = new double[n][n][k + 1];
        for (int left = 0; left < n; left++) {
            for (int right = 0; right < n; right++) {
                dp[left][right][1] = avg(preSum, left, n - 1);
            }
            for (int rest = 1; rest <= k; rest++) {
                dp[left][n - 1][rest] = avg(preSum, left, n - 1);
            }
        }
        for (int left = n - 1; left >= 0; left--) {
            for (int right = n - 2; right >= 0; right--) {
                for (int rest = 2; rest <= k; rest++) {
                    dp[left][right][rest] = Math.max(avg(preSum, left, right) + dp[right + 1][right + 1][rest - 1], dp[left][right + 1][rest]);
                }
            }
        }
        return dp[0][0][k];
    }

}
