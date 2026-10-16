package com.sean.leetcode.LeetCode1981;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-16 18:07
 * @Description: https://leetcode.cn/problems/minimize-the-difference-between-target-and-chosen-elements
 * 1981. 最小化目标值与所选元素的差
 * 给你一个大小为 m x n 的整数矩阵 mat 和一个整数 target 。
 * 从矩阵的 每一行 中选择一个整数，你的目标是 最小化 所有选中元素之 和 与目标值 target 的 绝对差 。
 * 返回 最小的绝对差 。
 * a 和 b 两数字的 绝对差 是 a - b 的绝对值。
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 70
 * 1 <= mat[i][j] <= 70
 * 1 <= target <= 800
 */
public class Solution {

    public int minimizeTheDifference(int[][] mat, int target) {
        int minSum = 0;
        int m = mat.length;
        boolean[][] dp = new boolean[m + 1][target * 2 + 1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            int minNum = Integer.MAX_VALUE;
            for (int num : mat[i - 1]) {
                minNum = Math.min(minNum, num);
                for (int j = num; j <= target * 2; j++) {
                    dp[i][j] |= dp[i - 1][j - num];
                }
            }
            minSum += minNum;
        }
        int res = Math.abs(minSum - target);
        for (int j = 0; j <= target * 2 && j - target <= res; j++) {
            if (dp[m][j]) {
                res = Math.min(res, Math.abs(j - target));
            }
        }
        return res;
    }

    public int minimizeTheDifference1(int[][] mat, int target) {
        int minSum = 0;
        int n = mat.length;
        boolean[] f = new boolean[target * 2 + 1];
        f[0] = true;
        for (int i = 1; i <= n; i++) {
            boolean[] nf = new boolean[target * 2 + 1];
            int minNum = Integer.MAX_VALUE;
            for (int num : mat[i - 1]) {
                minNum = Math.min(minNum, num);
                for (int j = num; j <= target * 2; j++) {
                    nf[j] |= f[j - num];
                }
            }
            f = nf;
            minSum += minNum;
        }
        int res = Math.abs(minSum - target);
        for (int j = 0; j <= target * 2 && j - target <= res; j++) {
            if (f[j]) {
                res = Math.min(res, Math.abs(j - target));
            }
        }
        return res;
    }

}
