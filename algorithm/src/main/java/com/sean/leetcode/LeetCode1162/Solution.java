package com.sean.leetcode.LeetCode1162;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-16 19:56
 * @Description: https://leetcode.cn/problems/as-far-from-land-as-possible/?plan=graph&plan_progress=zq56763
 * 1162. 地图分析
 * 你现在手里有一份大小为 n x n 的 网格 grid，上面的每个 单元格 都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地。
 * 请你找出一个海洋单元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的，并返回该距离。如果网格上只有陆地或者海洋，请返回 -1。
 * 我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个单元格之间的距离是 |x0 - x1| + |y0 - y1| 。
 */
public class Solution {

    public int maxDistance1(int[][] grid) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int n = grid.length;
        int[][] d = new int[n][n];
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    d[i][j] = 0;
                    queue.offer(new int[]{0, i, j});
                } else {
                    d[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] dir : dirs) {
                int nx = cell[1] + dir[0];
                int ny = cell[2] + dir[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }
                if (cell[0] + 1 < d[nx][ny]) {
                    d[nx][ny] = cell[0] + 1;
                    queue.offer(new int[]{d[nx][ny], nx, ny});
                }
            }
        }
        int res = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    res = Math.max(res, d[i][j]);
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public int maxDistance2(int[][] grid) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int n = grid.length;
        int[][] d = new int[n][n];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    d[i][j] = 0;
                    queue.offer(new int[]{i, j});
                } else {
                    d[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0];
            int y = cell[1];
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }
                if (d[nx][ny] > d[x][y] + 1) {
                    d[nx][ny] = d[x][y] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        int res = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    res = Math.max(res, d[i][j]);
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public int maxDistance(int[][] grid) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int n = grid.length;
        int[][] d = new int[n][n];
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    d[i][j] = 0;
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                } else {
                    d[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0];
            int y = cell[1];
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }
                if (d[nx][ny] > d[x][y] + 1) {
                    d[nx][ny] = d[x][y] + 1;
                    if (!visited[nx][ny]) {
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        int res = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    res = Math.max(res, d[i][j]);
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

}
