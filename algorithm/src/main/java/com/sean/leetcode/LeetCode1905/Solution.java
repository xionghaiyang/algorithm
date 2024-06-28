package com.sean.leetcode.LeetCode1905;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-15 19:10
 * @Description: https://leetcode.cn/problems/count-sub-islands/?plan=graph&plan_progress=zq56763
 * 1905. 统计子岛屿
 * 给你两个 m x n 的二进制矩阵 grid1 和 grid2 ，它们只包含 0 （表示水域）和 1 （表示陆地）。
 * 一个 岛屿 是由 四个方向 （水平或者竖直）上相邻的 1 组成的区域。
 * 任何矩阵以外的区域都视为水域。
 * 如果 grid2 的一个岛屿，被 grid1 的一个岛屿 完全 包含，也就是说 grid2 中该岛屿的每一个格子都被 grid1 中同一个岛屿完全包含，
 * 那么我们称 grid2 中的这个岛屿为 子岛屿 。
 */
public class Solution {

    public int countSubIslands1(int[][] grid1, int[][] grid2) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = grid1.length;
        int n = grid1[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    grid2[i][j] = 0;
                    int check = grid1[i][j];
                    while (!queue.isEmpty()) {
                        int[] cell = queue.poll();
                        for (int[] dir : dirs) {
                            int x = cell[0] + dir[0];
                            int y = cell[1] + dir[1];
                            if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1) {
                                queue.offer(new int[]{x, y});
                                grid2[x][y] = 0;
                                if (grid1[x][y] != 1) {
                                    check = 0;
                                }
                            }
                        }
                    }
                    res += check;
                }
            }
        }
        return res;
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    res += dfs(grid1, grid2, i, j) ? 1 : 0;
                }
            }
        }
        return res;
    }

    private boolean dfs(int[][] grid1, int[][] grid2, int i, int j) {
        int m = grid1.length;
        int n = grid1[0].length;
        grid2[i][j] = 0;
        boolean res = grid1[i][j] == 1;
        if (i - 1 >= 0 && grid2[i - 1][j] == 1) {
            res &= dfs(grid1, grid2, i - 1, j);
        }
        if (i + 1 < m && grid2[i + 1][j] == 1) {
            res &= dfs(grid1, grid2, i + 1, j);
        }
        if (j - 1 >= 0 && grid2[i][j - 1] == 1) {
            res &= dfs(grid1, grid2, i, j - 1);
        }
        if (j + 1 < n && grid2[i][j + 1] == 1) {
            res &= dfs(grid1, grid2, i, j + 1);
        }
        return res;
    }

}
