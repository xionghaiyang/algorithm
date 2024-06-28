package com.sean.leetcode.LeetCode2786;

/**
 * @Author xionghaiyang
 * @Date 2024-06-14 08:28
 * @Description https://leetcode.cn/problems/visit-array-positions-to-maximize-score/
 * 2786. 访问数组中的位置使分数最大
 * 给你一个下标从 0 开始的整数数组 nums 和一个正整数 x 。
 * 你 一开始 在数组的位置 0 处，你可以按照下述规则访问数组中的其他位置：
 * 如果你当前在位置 i ，那么你可以移动到满足 i < j 的 任意 位置 j 。
 * 对于你访问的位置 i ，你可以获得分数 nums[i] 。
 * 如果你从位置 i 移动到位置 j 且 nums[i] 和 nums[j] 的 奇偶性 不同，那么你将失去分数 x 。
 * 请你返回你能得到的 最大 得分之和。
 * 注意 ，你一开始的分数为 nums[0] 。
 */
public class Solution {

    public long maxScore(int[] nums, int x) {
        long res = nums[0];
        int n = nums.length;
        long[] dp = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        dp[nums[0] % 2] = nums[0];
        for (int i = 1; i < n; i++) {
            int index = nums[i] % 2;
            long cur = Math.max(dp[index] + nums[i], dp[1 - index] - x + nums[i]);
            res = Math.max(res, cur);
            dp[index] = Math.max(dp[index], cur);
        }
        return res;
    }

}
