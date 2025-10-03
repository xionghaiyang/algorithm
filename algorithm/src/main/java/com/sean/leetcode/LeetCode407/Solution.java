package com.sean.leetcode.LeetCode407;

import java.util.PriorityQueue;

/**
 * @Author xionghaiyang
 * @Date 2025-10-03 17:44
 * @Description https://leetcode.cn/problems/trapping-rain-water-ii
 * 407. 接雨水 II
 * 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 * m == heightMap.length
 * n == heightMap[i].length
 * 1 <= m, n <= 200
 * 0 <= heightMap[i][j] <= 2 * 10^4
 */
public class Solution {

    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length <= 2 || heightMap[0].length <= 2) {
            return 0;
        }
        int m = heightMap.length, n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    heap.offer(new int[]{i, j, heightMap[i][j]});
                    visited[i][j] = true;
                }
            }
        }
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int res = 0;
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int x = cur[0], y = cur[1];
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]) {
                    if (cur[2] > heightMap[nx][ny]) {
                        res += cur[2] - heightMap[nx][ny];
                    }
                    heap.offer(new int[]{nx, ny, Math.max(heightMap[nx][ny], cur[2])});
                    visited[nx][ny] = true;
                }
            }
        }
        return res;
    }

}
