package com.sean.leetcode.LeetCode897;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-18 08:19
 * @Description: https://leetcode.cn/problems/sum-of-subsequence-widths/
 * 891. 子序列宽度之和
 * 一个序列的 宽度 定义为该序列中最大元素和最小元素的差值。
 * 给你一个整数数组 nums ，返回 nums 的所有非空 子序列 的 宽度之和 。
 * 由于答案可能非常大，请返回对 10^9 + 7 取余 后的结果。
 * 子序列 定义为从一个数组里删除一些（或者不删除）元素，但不改变剩下元素的顺序得到的数组。
 * 例如，[3,6,2,7] 就是数组 [0,3,1,6,2,2,7] 的一个子序列。
 */
public class Solution {

    public int sumSubseqWidths(int[] nums) {
        int mod = 1000000007;
        Arrays.sort(nums);
        int n = nums.length;
        long maxSum = 0, minSum = 0;
        for (int i = 0; i < n; i++) {
            minSum = (minSum * 2 + nums[i]) % mod;
            maxSum = (maxSum * 2 + nums[n - i - 1]) % mod;
        }
        return (int) ((maxSum + mod - minSum) % mod);
    }

}
