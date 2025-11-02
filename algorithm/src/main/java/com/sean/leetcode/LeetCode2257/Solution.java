package com.sean.leetcode.LeetCode2257;

/**
 * @Author xionghaiyang
 * @Date 2025-11-02 12:19
 * @Description https://leetcode.cn/problems/count-unguarded-cells-in-the-grid
 * 2257. 统计网格图中没有被保卫的格子数
 * 给你两个整数 m 和 n 表示一个下标从 0 开始的 m x n 网格图。
 * 同时给你两个二维整数数组 guards 和 walls ，其中 guards[i] = [rowi, coli] 且 walls[j] = [rowj, colj] ，分别表示第 i 个警卫和第 j 座墙所在的位置。
 * 一个警卫能看到 4 个坐标轴方向（即东、南、西、北）的 所有 格子，除非他们被一座墙或者另外一个警卫 挡住 了视线。
 * 如果一个格子能被 至少 一个警卫看到，那么我们说这个格子被 保卫 了。
 * 请你返回空格子中，有多少个格子是 没被保卫 的。
 * 1 <= m, n <= 10^5
 * 2 <= m * n <= 10^5
 * 1 <= guards.length, walls.length <= 5 * 10^4
 * 2 <= guards.length + walls.length <= m * n
 * guards[i].length == walls[j].length == 2
 * 0 <= rowi, rowj < m
 * 0 <= coli, colj < n
 * guards 和 walls 中所有位置 互不相同 。
 */
public class Solution {

    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        int[][] guarded = new int[m][n];
        for (int[] guard : guards) {
            guarded[guard[0]][guard[1]] = -1;
        }
        for (int[] wall : walls) {
            guarded[wall[0]][wall[1]] = -1;
        }
        for (int[] guard : guards) {
            for (int[] dir : dirs) {
                int dx = dir[0], dy = dir[1];
                int x = guard[0] + dx, y = guard[1] + dy;
                while (x >= 0 && x < m && y >= 0 && y < n && guarded[x][y] != -1) {
                    guarded[x][y] = 1;
                    x += dx;
                    y += dy;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (guarded[i][j] == 0) {
                    res++;
                }
            }
        }
        return res;
    }

}
