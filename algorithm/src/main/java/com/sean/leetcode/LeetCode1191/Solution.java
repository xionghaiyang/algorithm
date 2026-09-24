package com.sean.leetcode.LeetCode1191;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-24 17:34
 * @Description: https://leetcode.cn/problems/k-concatenation-maximum-sum
 * 1191. K 次串联后最大子数组之和
 * 给定一个整数数组 arr 和一个整数 k ，通过重复 k 次来修改数组。
 * 例如，如果 arr = [1, 2] ， k = 3 ，那么修改后的数组将是 [1, 2, 1, 2, 1, 2] 。
 * 返回修改后的数组中的最大的子数组之和。
 * 注意，子数组长度可以是 0，在这种情况下它的总和也是 0。
 * 由于 结果可能会很大，需要返回结果对 10^9 + 7 取 模。
 * 1 <= arr.length <= 10^5
 * 1 <= k <= 10^5
 * -10^4 <= arr[i] <= 10^4
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int kConcatenationMaxSum(int[] arr, int k) {
        if (k == 1) {
            return maxSum(arr, 1);
        }
        long res = maxSum(arr, 2);
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        res += (long) Math.max(sum, 0) * (k - 2);
        return (int) (res % MOD);
    }

    private int maxSum(int[] arr, int k) {
        int res = 0, sum = 0;
        for (int i = 0; i < k; i++) {
            for (int num : arr) {
                sum = Math.max(sum, 0) + num;
                res = Math.max(res, sum);
            }
        }
        return res;
    }

}
