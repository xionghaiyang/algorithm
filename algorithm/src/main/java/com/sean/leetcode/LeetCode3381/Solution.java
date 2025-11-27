package com.sean.leetcode.LeetCode3381;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-11-27 12:24
 * @Description https://leetcode.cn/problems/maximum-subarray-sum-with-length-divisible-by-k
 * 3381. 长度可被 K 整除的子数组的最大元素和
 * 给你一个整数数组 nums 和一个整数 k 。
 * 返回 nums 中一个 非空子数组 的 最大 和，要求该子数组的长度可以 被 k 整除。
 * 1 <= k <= nums.length <= 2 * 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class Solution {

    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] minS = new long[k];
        Arrays.fill(minS, 0, k - 1, Long.MAX_VALUE / 2);
        long res = Long.MIN_VALUE, sum = 0;
        for (int j = 0; j < n; j++) {
            sum += nums[j];
            int i = j % k;
            res = Math.max(res, sum - minS[i]);
            minS[i] = Math.min(minS[i], sum);
        }
        return res;
    }

}
