package com.sean.leetcode.LeetCode3651;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2026-01-28 12:08
 * @Description https://leetcode.cn/problems/minimum-cost-path-with-teleportations
 * 3651. 带传送的最小路径成本
 * 给你一个 m x n 的二维整数数组 grid 和一个整数 k。
 * 你从左上角的单元格 (0, 0) 出发，目标是到达右下角的单元格 (m - 1, n - 1)。
 * 有两种移动方式可用：
 * 普通移动：你可以从当前单元格 (i, j) 向右或向下移动，即移动到 (i, j + 1)（右）或 (i + 1, j)（下）。
 * 成本为目标单元格的值。
 * 传送：你可以从任意单元格 (i, j) 传送到任意满足 grid[x][y] <= grid[i][j] 的单元格 (x, y)；此移动的成本为 0。
 * 你最多可以传送 k 次。
 * 返回从 (0, 0) 到达单元格 (m - 1, n - 1) 的 最小 总成本。
 * 2 <= m, n <= 80
 * m == grid.length
 * n == grid[i].length
 * 0 <= grid[i][j] <= 10^4
 * 0 <= k <= 10
 */
public class Solution {

    public int minCost(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                list.add(new int[]{i, j});
            }
        }
        list.sort(Comparator.comparingInt(e -> grid[e[0]][e[1]]));
        int[][] costs = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(costs[i], Integer.MAX_VALUE);
        }
        for (int t = 0; t <= k; t++) {
            int minCost = Integer.MAX_VALUE;
            for (int i = 0, j = 0; i < list.size(); i++) {
                minCost = Math.min(minCost, costs[list.get(i)[0]][list.get(i)[1]]);
                if (i + 1 < list.size() && grid[list.get(i)[0]][list.get(i)[1]] == grid[list.get(i + 1)[0]][list.get(i + 1)[1]]) {
                    continue;
                }
                for (int r = j; r <= i; r++) {
                    costs[list.get(r)[0]][list.get(r)[1]] = minCost;
                }
                j = i + 1;
            }
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    if (i == m - 1 && j == n - 1) {
                        costs[i][j] = 0;
                        continue;
                    }
                    if (i != m - 1) {
                        costs[i][j] = Math.min(costs[i][j], costs[i + 1][j] + grid[i + 1][j]);
                    }
                    if (j != n - 1) {
                        costs[i][j] = Math.min(costs[i][j], costs[i][j + 1] + grid[i][j + 1]);
                    }
                }
            }
        }
        return costs[0][0];
    }

}
