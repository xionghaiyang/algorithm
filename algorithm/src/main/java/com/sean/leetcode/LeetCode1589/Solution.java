package com.sean.leetcode.LeetCode1589;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-28 17:18
 * @Description: https://leetcode.cn/problems/maximum-sum-obtained-of-any-permutation/description/
 * 1589. 所有排列中的最大和
 * 有一个整数数组 nums ，和一个查询数组 requests ，其中 requests[i] = [starti, endi] 。
 * 第 i 个查询求 nums[starti] + nums[starti + 1] + ... + nums[endi - 1] + nums[endi] 的结果 ，
 * starti 和 endi 数组索引都是 从 0 开始 的。
 * 你可以任意排列 nums 中的数字，请你返回所有查询结果之和的最大值。
 * 由于答案可能会很大，请你将它对 109 + 7 取余 后返回。
 */
public class Solution {

    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int mod = 1000000007;
        int n = nums.length;
        int[] counts = new int[n];
        for (int[] request : requests) {
            int start = request[0];
            int end = request[1];
            counts[start]++;
            if (end + 1 < n) {
                counts[end + 1]--;
            }
        }
        for (int i = 1; i < n; i++) {
            counts[i] += counts[i - 1];
        }
        Arrays.sort(counts);
        Arrays.sort(nums);
        long res = 0;
        for (int i = n - 1; i >= 0 && counts[i] > 0; i--) {
            res += (long) nums[i] * counts[i];
        }
        return (int) (res % mod);
    }

}
