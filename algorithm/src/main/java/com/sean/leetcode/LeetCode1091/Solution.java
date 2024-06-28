package com.sean.leetcode.LeetCode1091;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-17 07:14
 * @Description: https://leetcode.cn/problems/shortest-path-in-binary-matrix/?plan=graph&plan_progress=zq56763
 * 1091. 二进制矩阵中的最短路径
 * 给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
 * 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：
 * 路径途经的所有单元格都的值都是 0 。
 * 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
 * 畅通路径的长度 是该路径途经的单元格总数。
 */
public class Solution {

    public int shortestPathBinaryMatrix(int[][] grid) {
        int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        if (grid[0][0] != 0) {
            return -1;
        }
        if (n == 1) {
            return 1;
        }
        int res = 1;
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int x = cell[0];
                int y = cell[1];
                for (int[] dir : dirs) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 0 && !visited[nx][ny]) {
                        if (nx == n - 1 && ny == n - 1) {
                            return res + 1;
                        }
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
            res++;
        }
        return -1;
    }

}
