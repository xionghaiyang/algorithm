package com.sean.leetcode.LeetCode1879;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-29 15:57
 * @Description: https://leetcode.cn/problems/minimum-xor-sum-of-two-arrays/description/
 * 1879. 两个数组最小的异或值之和
 * 给你两个整数数组 nums1 和 nums2 ，它们长度都为 n 。
 * 两个数组的 异或值之和 为 (nums1[0] XOR nums2[0]) + (nums1[1] XOR nums2[1]) + ... + (nums1[n - 1] XOR nums2[n - 1]) （下标从 0 开始）。
 * 比方说，[1,2,3] 和 [3,2,1] 的 异或值之和 等于 (1 XOR 3) + (2 XOR 2) + (3 XOR 1) = 2 + 0 + 2 = 4 。
 * 请你将 nums2 中的元素重新排列，使得 异或值之和 最小 。
 * 请你返回重新排列之后的 异或值之和 。
 */
public class Solution {

    public int minimumXORSum(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int mask = 1; mask < (1 << n); mask++) {
            int i = Integer.bitCount(mask) - 1;
            for (int j = 0; j < n; j++) {
                if (((mask >> j) & 1) == 1) {
                    int premask = mask ^ (1 << j);
                    dp[mask] = Math.min(dp[mask], dp[premask] + (nums1[i] ^ nums2[j]));
                }
            }
        }
        return dp[(1 << n) - 1];
    }

}
