package com.sean.leetcode.LeetCode2617;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-22 18:08
 * @Description: https://leetcode.cn/problems/minimum-number-of-visited-cells-in-a-grid/
 * 2617. 网格图中最少访问的格子数
 * 给你一个下标从 0 开始的 m x n 整数矩阵 grid 。你一开始的位置在 左上角 格子 (0, 0) 。
 * 当你在格子 (i, j) 的时候，你可以移动到以下格子之一：
 * 满足 j < k <= grid[i][j] + j 的格子 (i, k) （向右移动），或者
 * 满足 i < k <= grid[i][j] + i 的格子 (k, j) （向下移动）。
 * 请你返回到达 右下角 格子 (m - 1, n - 1) 需要经过的最少移动格子数，如果无法到达右下角格子，请你返回 -1 。
 */
public class Solution {

    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], -1);
        }
        dist[0][0] = 1;
        PriorityQueue<int[]>[] row = new PriorityQueue[m];
        PriorityQueue<int[]>[] col = new PriorityQueue[n];
        for (int i = 0; i < m; i++) {
            row[i] = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        }
        for (int i = 0; i < n; i++) {
            col[i] = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                while (!row[i].isEmpty() && row[i].peek()[1] + grid[i][row[i].peek()[1]] < j) {
                    row[i].poll();
                }
                if (!row[i].isEmpty()) {
                    dist[i][j] = update(dist[i][j], dist[i][row[i].peek()[1]] + 1);
                }
                while (!col[j].isEmpty() && col[j].peek()[1] + grid[col[j].peek()[1]][j] < i) {
                    col[j].poll();
                }
                if (!col[j].isEmpty()) {
                    dist[i][j] = update(dist[i][j], dist[col[j].peek()[1]][j] + 1);
                }
                if (dist[i][j] != -1) {
                    row[i].offer(new int[]{dist[i][j], j});
                    col[j].offer(new int[]{dist[i][j], i});
                }
            }
        }
        return dist[m - 1][n - 1];
    }

    private int update(int x, int y) {
        return x == -1 || y < x ? y : x;
    }

}
