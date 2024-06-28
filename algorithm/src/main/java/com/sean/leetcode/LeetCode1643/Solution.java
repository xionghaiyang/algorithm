package com.sean.leetcode.LeetCode1643;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-19 12:26
 * @Description: https://leetcode.cn/problems/kth-smallest-instructions/
 * 1643. 第 K 条最小指令
 * Bob 站在单元格 (0, 0) ，想要前往目的地 destination ：(row, column) 。
 * 他只能向 右 或向 下 走。
 * 你可以为 Bob 提供导航 指令 来帮助他到达目的地 destination 。
 * 指令 用字符串表示，其中每个字符：
 * 'H' ，意味着水平向右移动
 * 'V' ，意味着竖直向下移动
 * 能够为 Bob 导航到目的地 destination 的指令可以有多种，例如，如果目的地 destination 是 (2, 3)，"HHHVV" 和 "HVHVH" 都是有效 指令 。
 * 然而，Bob 很挑剔。因为他的幸运数字是 k，他想要遵循 按字典序排列后的第 k 条最小指令 的导航前往目的地 destination 。
 * k  的编号 从 1 开始 。
 * 给你一个整数数组 destination 和一个整数 k ，请你返回可以为 Bob 提供前往目的地 destination 导航的 按字典序排列后的第 k 条最小指令 。
 */
public class Solution {

    private int[][] dp;
    private int rows;
    private int cols;

    public String kthSmallestPath(int[] destination, int k) {
        rows = destination[0];
        cols = destination[1];
        //每个点到达终点的路径数
        dp = new int[rows + 1][cols + 1];
        dfs(0, 0);
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        while (i != rows && j != cols) {
            if (dp[i][j + 1] >= k) {
                j++;
                sb.append("H");
            } else {
                k -= dp[i][j + 1];
                i++;
                sb.append("V");
            }
        }
        if (i == rows) {
            for (int b = cols - j; b > 0; b--) {
                sb.append("H");
            }
        }
        if (j == cols) {
            for (int b = rows - i; b > 0; b--) {
                sb.append("V");
            }
        }
        return sb.toString();
    }

    private int dfs(int i, int j) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        if (i == rows || j == cols) {
            dp[i][j] = 1;
            return dp[i][j];
        }
        dp[i][j] = dfs(i + 1, j) + dfs(i, j + 1);
        return dp[i][j];
    }

}
