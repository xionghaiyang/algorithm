package com.sean.leetcode.LeetCode1463;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-05-07 06:56
 * @Description https://leetcode.cn/problems/cherry-pickup-ii/
 * 1463. 摘樱桃 II
 * 给你一个 rows x cols 的矩阵 grid 来表示一块樱桃地。
 * grid 中每个格子的数字表示你能获得的樱桃数目。
 * 你有两个机器人帮你收集樱桃，机器人 1 从左上角格子 (0,0) 出发，机器人 2 从右上角格子 (0, cols-1) 出发。
 * 请你按照如下规则，返回两个机器人能收集的最多樱桃数目：
 * 从格子 (i,j) 出发，机器人可以移动到格子 (i+1, j-1)，(i+1, j) 或者 (i+1, j+1) 。
 * 当一个机器人经过某个格子时，它会把该格子内所有的樱桃都摘走，然后这个位置会变成空格子，即没有樱桃的格子。
 * 当两个机器人同时到达同一个格子时，它们中只有一个可以摘到樱桃。
 * 两个机器人在任意时刻都不能移动到 grid 外面。
 * 两个机器人最后都要到达 grid 最底下一行。
 */
public class Solution {

    public int cherryPickup(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] f = new int[n][n];
        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(f[i], -1);
            Arrays.fill(g[i], -1);
        }
        f[0][n - 1] = grid[0][0] + grid[0][n - 1];
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    int best = -1;
                    for (int dj = j - 1; dj <= j + 1; dj++) {
                        for (int dk = k - 1; dk <= k + 1; dk++) {
                            if (dj >= 0 && dj < n && dk >= 0 && dk < n && f[dj][dk] != -1) {
                                best = Math.max(best, f[dj][dk] + (j == k ? grid[i][j] : grid[i][j] + grid[i][k]));
                            }
                        }
                    }
                    g[j][k] = best;
                }
            }
            int[][] temp = f;
            f = g;
            g = temp;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                res = Math.max(res, f[j][k]);
            }
        }
        return res;
    }

}
