package com.sean.leetcode.LeetCode813;

/**
 * @Author: xionghaiyang
 * @Date: 2022-11-28 08:37
 * @Description: https://leetcode.cn/problems/largest-sum-of-averages
 * 813. 最大平均值和的分组
 * 给定数组 nums 和一个整数 k 。
 * 我们将给定的数组 nums 分成 最多 k 个相邻的非空子数组 。 
 * 分数 由每个子数组内的平均值的总和构成。
 * 注意我们必须使用 nums 数组中的每一个数进行分组，并且分数不一定需要是整数。
 * 返回我们所能得到的最大 分数 是多少。
 * 答案误差在 10^-6 内被视为是正确的。
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 */
public class Solution {

    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[] preSum = new double[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        //dp[i][j]:前i个元素划分成j份的最大平均和
        double[][] dp = new double[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                if (j == 1) {
                    dp[i][j] = preSum[i] / i;
                } else {
                    for (int i0 = 2; i0 <= i; i0++) {
                        dp[i][j] = Math.max(dp[i][j], dp[i0 - 1][j - 1] + (preSum[i] - preSum[i0 - 1]) / (i - i0 + 1));
                    }
                }
            }
        }
        return dp[n][k];
    }

}
