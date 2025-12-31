package com.sean.leetcode.LeetCode1970;

/**
 * @Author xionghaiyang
 * @Date 2025-12-31 12:51
 * @Description https://leetcode.cn/problems/last-day-where-you-can-still-cross
 * 1970. 你能穿过矩阵的最后一天
 * 给你一个下标从 1 开始的二进制矩阵，其中 0 表示陆地，1 表示水域。
 * 同时给你 row 和 col 分别表示矩阵中行和列的数目。
 * 一开始在第 0 天，整个 矩阵都是 陆地 。
 * 但每一天都会有一块新陆地被 水 淹没变成水域。
 * 给你一个下标从 1 开始的二维数组 cells ，其中 cells[i] = [ri, ci] 表示在第 i 天，第 ri 行 ci 列（下标都是从 1 开始）的陆地会变成 水域 （也就是 0 变成 1 ）。
 * 你想知道从矩阵最 上面 一行走到最 下面 一行，且只经过陆地格子的 最后一天 是哪一天。
 * 你可以从最上面一行的 任意 格子出发，到达最下面一行的 任意 格子。
 * 你只能沿着 四个 基本方向移动（也就是上下左右）。
 * 请返回只经过陆地格子能从最 上面 一行走到最 下面 一行的 最后一天 。
 * 2 <= row, col <= 2 * 10^4
 * 4 <= row * col <= 2 * 10^4
 * cells.length == row * col
 * 1 <= ri <= row
 * 1 <= ci <= col
 * cells 中的所有格子坐标都是 唯一 的。
 */
public class Solution {

    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};//左右上下

    public int latestDayToCross(int row, int col, int[][] cells) {
        //0:水
        //1:陆地（未被感染）
        //2:陆地（已被感染）
        int[][] grid = new int[row][col];
        for (int day = cells.length - 1; ; day--) {
            int r = cells[day][0] - 1, c = cells[day][1] - 1;
            grid[r][c] = 1;//未被感染的陆地
            if (canReachFromTop(r, c, grid) && dfs(r, c, grid)) {
                return day;
            }
        }
    }

    //能否从第一行达到(r,c)
    private boolean canReachFromTop(int r, int c, int[][] grid) {
        if (r == 0) {//已经是第一行
            return true;
        }
        for (int[] dir : DIRS) {
            int x = r + dir[0], y = c + dir[1];
            if (0 <= x && x < grid.length && 0 <= y && y < grid[x].length && grid[x][y] == 2) {
                return true;
            }
        }
        return false;
    }

    //从(r,c)出发，能否到达最后一行
    private boolean dfs(int r, int c, int[][] grid) {
        int m = grid.length;
        if (r == m - 1) {
            return true;
        }
        grid[r][c] = 2;//感染
        for (int[] dir : DIRS) {
            int x = r + dir[0], y = c + dir[1];
            if (0 <= x && x < m && 0 <= y && y < grid[x].length && grid[x][y] == 1 && dfs(x, y, grid)) {
                return true;
            }
        }
        return false;
    }

}
