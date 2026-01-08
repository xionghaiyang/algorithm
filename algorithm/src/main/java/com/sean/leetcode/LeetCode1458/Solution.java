package com.sean.leetcode.LeetCode1458;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-01-08 08:16
 * @Description https://leetcode.cn/problems/max-dot-product-of-two-subsequences
 * 1458. 两个子序列的最大点积
 * 给你两个数组 nums1 和 nums2 。
 * 请你返回 nums1 和 nums2 中两个长度相同的 非空 子序列的最大点积。
 * 数组的非空子序列是通过删除原数组中某些元素（可能一个也不删除）后剩余数字组成的序列，但不能改变数字间相对顺序。
 * 比方说，[2,3,5] 是 [1,2,3,4,5] 的一个子序列而 [1,5,3] 不是。
 * 1 <= nums1.length, nums2.length <= 500
 * -1000 <= nums1[i], nums2[i] <= 1000
 */
public class Solution {

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }
        return dfs(nums1, nums2, memo, m - 1, n - 1);
    }

    private int dfs(int[] nums1, int[] nums2, int[][] memo, int i, int j) {
        if (i < 0 || j < 0) {
            return Integer.MIN_VALUE;
        }
        if (memo[i][j] != Integer.MAX_VALUE) {
            return memo[i][j];
        }
        int res1 = Math.max(dfs(nums1, nums2, memo, i - 1, j - 1), 0) + nums1[i] * nums2[j];
        int res2 = dfs(nums1, nums2, memo, i - 1, j);
        int res3 = dfs(nums1, nums2, memo, i, j - 1);
        return memo[i][j] = Math.max(res1, Math.max(res2, res3));
    }

}
