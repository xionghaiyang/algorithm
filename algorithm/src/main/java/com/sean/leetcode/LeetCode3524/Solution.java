package com.sean.leetcode.LeetCode3524;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-21 05:19
 * @Description: https://leetcode.cn/problems/find-x-value-of-array-i
 * 3524. 求出数组的 X 值 I
 * 给你一个由 正 整数组成的数组 nums，以及一个 正 整数 k。
 * 你可以对 nums 执行 一次 操作，该操作中可以移除任意 不重叠 的前缀和后缀，使得 nums 仍然 非空 。
 * 你需要找出 nums 的 x 值，即在执行操作后，剩余元素的 乘积 除以 k 后的 余数 为 x 的操作数量。
 * 返回一个大小为 k 的数组 result，其中 result[x] 表示对于 0 <= x <= k - 1，nums 的 x 值。
 * 数组的 前缀 指从数组起始位置开始到数组中任意位置的一段连续子数组。
 * 数组的 后缀 是指从数组中任意位置开始到数组末尾的一段连续子数组。
 * 子数组 是数组中一段连续的元素序列。
 * 注意，在操作中选择的前缀和后缀可以是 空的 。
 * 1 <= nums[i] <= 10^9
 * 1 <= nums.length <= 10^5
 * 1 <= k <= 5
 */
public class Solution {

    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
        long[] res = new long[k];
        long[][] f = new long[n + 1][k];
        for (int i = 0; i < n; i++) {
            int v = nums[i] % k;
            f[i + 1][v] = 1;
            for (int u = 0; u < k; u++) {
                f[i + 1][u * v % k] += f[i][u];
            }
            for (int u = 0; u < k; u++) {
                res[u] += f[i + 1][u];
            }
        }
        return res;
    }

    public long[] resultArray1(int[] nums, int k) {
        long[] res = new long[k];
        int[] f = new int[k];
        for (int v : nums) {
            v %= k;
            int[] nf = new int[k];
            nf[v] = 1;
            for (int u = 0; u < k; u++) {
                nf[u * v % k] += f[u];
            }
            f = nf;
            for (int u = 0; u < k; u++) {
                res[u] += f[u];
            }
        }
        return res;
    }

}
