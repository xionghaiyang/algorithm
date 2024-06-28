package com.sean.leetcode.LeetCode2965;

/**
 * @Author xionghaiyang
 * @Date 2024-05-31 10:29
 * @Description https://leetcode.cn/problems/find-missing-and-repeated-values/
 * 2965. 找出缺失和重复的数字
 * 给你一个下标从 0 开始的二维整数矩阵 grid，大小为 n * n ，其中的值在 [1, n^2] 范围内。
 * 除了 a 出现 两次，b 缺失 之外，每个整数都 恰好出现一次 。
 * 任务是找出重复的数字a 和缺失的数字 b 。
 * 返回一个下标从 0 开始、长度为 2 的整数数组 ans ，其中 ans[0] 等于 a ，ans[1] 等于 b 。
 */
public class Solution {

    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        int[] arr = new int[n * n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[grid[i][j]]++;
            }
        }
        int[] res = new int[2];
        for (int i = 1; i <= n * n; i++) {
            if (arr[i] == 2) {
                res[0] = i;
            }
            if (arr[i] == 0) {
                res[1] = i;
            }
        }
        return res;
    }

    public int[] findMissingAndRepeatedValues1(int[][] grid) {
        int n = grid.length;
        int xor = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                xor ^= grid[i][j];
            }
        }
        for (int i = 1; i <= n * n; i++) {
            xor ^= i;
        }
        int lsb = xor & (-xor);
        int[] res = new int[2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[(grid[i][j] & lsb) == 0 ? 0 : 1] ^= grid[i][j];
            }
        }
        for (int i = 1; i <= n * n; i++) {
            res[(i & lsb) == 0 ? 0 : 1] ^= i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == res[0]) {
                    return res;
                }
            }
        }
        return new int[]{res[1], res[0]};
    }

}
