package com.sean.leetcode.LeetCode120;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-28 17:03
 * @Description: https://leetcode.cn/problems/triangle/?envType=study-plan&id=dong-tai-gui-hua-ru-men&plan=dynamic-programming&plan_progress=yn1qp15
 * 120. 三角形最小路径和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 */
public class Solution {

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(triangle, 0, 0, dp);
    }

    private int process(List<List<Integer>> triangle, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int n = triangle.size();
        if (i == n - 1) {
            dp[i][j] = triangle.get(n - 1).get(j);
            return triangle.get(n - 1).get(j);
        }
        int res = triangle.get(i).get(j) + process(triangle, i + 1, j, dp);
        if (j + 1 < triangle.get(i + 1).size()) {
            res = Math.min(res, triangle.get(i).get(j) + process(triangle, i + 1, j + 1, dp));
        }
        dp[i][j] = res;
        return res;
    }

}
