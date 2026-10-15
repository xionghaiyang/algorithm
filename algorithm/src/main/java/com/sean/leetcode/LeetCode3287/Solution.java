package com.sean.leetcode.LeetCode3287;

/**
 * @Author xionghaiyang
 * @Date 2025-01-18 19:53
 * @Description https://leetcode.cn/problems/find-the-maximum-sequence-value-of-array
 * 3287. 求出数组中最大序列值
 * 给你一个整数数组 nums 和一个 正 整数 k 。
 * 定义长度为 2 * x 的序列 seq 的 值 为：
 * (seq[0] OR seq[1] OR ... OR seq[x - 1]) XOR (seq[x] OR seq[x + 1] OR ... OR seq[2 * x - 1]).
 * 请你求出 nums 中所有长度为 2 * k 的子序列的 最大值 。
 * 2 <= nums.length <= 400
 * 1 <= nums[i] < 2^7
 * 1 <= k <= nums.length / 2
 */
public class Solution {

    private static final int MAX = 1 << 7;

    public int maxValue(int[] nums, int k) {
        int n = nums.length;
        boolean[][] suf = new boolean[n - k + 1][];
        boolean[][] f = new boolean[k + 1][MAX];
        f[0][0] = true;
        for (int i = n - 1; i >= k; i--) {
            int v = nums[i];
            for (int j = Math.min(k - 1, n - 1 - i); j >= 0; j--) {
                for (int x = 0; x < MAX; x++) {
                    if (f[j][x]) {
                        f[j + 1][x | v] = true;
                    }
                }
            }
            if (i <= n - k) {
                suf[i] = f[k].clone();
            }
        }
        int res = 0;
        f = new boolean[k + 1][MAX];
        f[0][0] = true;
        for (int i = 0; i < n - k; i++) {
            int v = nums[i];
            for (int j = Math.min(k - 1, i); j >= 0; j--) {
                for (int x = 0; x < MAX; x++) {
                    if (f[j][x]) {
                        f[j + 1][x | v] = true;
                    }
                }
            }
            if (i < k - 1) {
                continue;
            }
            for (int x = 0; x < MAX; x++) {
                if (f[k][x]) {
                    for (int y = 0; y < MAX; y++) {
                        if (suf[i + 1][y]) {
                            res = Math.max(res, x ^ y);
                        }
                    }
                }
                if (res == MAX - 1) {
                    return res;
                }
            }
        }
        return res;
    }

}
