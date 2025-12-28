package com.sean.leetcode.LeetCode3788;

/**
 * @Author xionghaiyang
 * @Date 2025-12-28 12:36
 * @Description https://leetcode.cn/problems/maximum-score-of-a-split
 * 3788. 分割的最大得分
 * 给你一个长度为 n 的整数数组 nums。
 * 请你选出一个下标 i 以分割数组，该下标满足 0 <= i < n - 1。
 * 对于选择的分割下标 i：
 * 令 prefixSum(i) 表示数组前缀的和，即 nums[0] + nums[1] + ... + nums[i]。
 * 令 suffixMin(i) 表示数组后缀的最小值，即 nums[i + 1], nums[i + 2], ..., nums[n - 1] 中的最小值。
 * 在下标 i 的 分割得分 定义为：
 * score(i) = prefixSum(i) - suffixMin(i)
 * 返回所有有效分割下标中 最大 的分割得分。
 * 2 <= nums.length <= 10^5
 * -10^9​​​​​​​ <= nums[i] <= 10^9
 */
public class Solution {

    public long maximumScore(int[] nums) {
        int n = nums.length;
        long prefixSum = 0;
        for (int num : nums) {
            prefixSum += num;
        }
        int suffixMin = nums[n - 1];
        long res = Long.MIN_VALUE;
        for (int i = n - 1; i > 0; i--) {
            prefixSum -= nums[i];
            suffixMin = Math.min(suffixMin, nums[i]);
            res = Math.max(res, prefixSum - suffixMin);
        }
        return res;
    }

}
