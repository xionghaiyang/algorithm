package com.sean.leetcode.LeetCode1039;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-03 20:36
 * @Description: https://leetcode.cn/problems/minimum-score-triangulation-of-polygon
 * 1039. 多边形三角剖分的最低得分
 * 你有一个凸的 n 边形，其每个顶点都有一个整数值。
 * 给定一个整数数组 values ，其中 values[i] 是第 i 个顶点的值（即 顺时针顺序 ）。
 * 假设将多边形 剖分 为 n - 2 个三角形。
 * 对于每个三角形，该三角形的值是顶点标记的乘积，三角剖分的分数是进行三角剖分后所有 n - 2 个三角形的值之和。
 * 返回 多边形进行三角剖分后可以得到的最低分 。
 * n == values.length
 * 3 <= n <= 50
 * 1 <= values[i] <= 100
 */
public class Solution {

    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(values, memo, 0, n - 1);
    }

    private int dfs(int[] values, int[][] memo, int i, int j) {
        if (i + 1 == j) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int res = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            res = Math.min(res, dfs(values, memo, i, k) + dfs(values, memo, k, j) + values[i] * values[j] * values[k]);
        }
        return memo[i][j] = res;
    }

}
