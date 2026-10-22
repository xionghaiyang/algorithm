package com.sean.leetcode.LeetCode3836;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-22 12:39
 * @Description: https://leetcode.cn/problems/maximum-score-using-exactly-k-pairs
 * 3836. 恰好 K 个下标对的最大得分
 * 给你两个长度分别为 n 和 m 的整数数组 nums1 和 nums2，以及一个整数 k。
 * 你必须 恰好 选择 k 对下标 (i1, j1), (i2, j2), ..., (ik, jk)，使得：
 * 0 <= i1 < i2 < ... < ik < n
 * 0 <= j1 < j2 < ... < jk < m
 * 对于每对选择的下标 (i, j)，你将获得 nums1[i] * nums2[j] 的得分。
 * 总 得分 是所有选定下标对的乘积的 总和。
 * 返回一个整数，表示可以获得的 最大 总得分。
 * 1 <= n == nums1.length <= 100
 * 1 <= m == nums2.length <= 100
 * -10^6 <= nums1[i], nums2[i] <= 10^6
 * 1 <= k <= min(n, m)
 */
public class Solution {

    public long maxScore(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        long[][][] memo = new long[m][n][k + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], Long.MIN_VALUE);
            }
        }
        return process(nums1, nums2, memo, m - 1, n - 1, k);
    }

    private long process(int[] nums1, int[] nums2, long[][][] memo, int i, int j, int k) {
        if (k == 0) {
            return 0;
        }
        if (i + 1 < k || j + 1 < k) {
            return Long.MIN_VALUE;
        }
        if (memo[i][j][k] != Long.MIN_VALUE) {
            return memo[i][j][k];
        }
        return memo[i][j][k] = Math.max(Math.max(process(nums1, nums2, memo, i - 1, j, k), process(nums1, nums2, memo, i, j - 1, k)), process(nums1, nums2, memo, i - 1, j - 1, k - 1) + (long) nums1[i] * nums2[j]);
    }

}
