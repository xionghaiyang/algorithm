package com.sean.leetcode.LeetCode1020;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-14 11:35
 * @Description: https://leetcode.cn/problems/number-of-enclaves/?plan=graph&plan_progress=zq56763
 * 1020. 飞地的数量
 * 给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
 * 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
 * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
 */
public class Solution {

    class UnionFind {
        int[] parent;
        boolean[] flag;
        int[] help;
        int[] size;
        int set;

        UnionFind(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            flag = new boolean[m * n];
            help = new int[m * n];
            size = new int[m * n];
            set = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        int index = i * n + j;
                        parent[index] = index;
                        if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                            flag[index] = true;
                        } else {
                            set++;
                        }
                        size[index]++;
                    }
                }
            }
        }

        int find(int i) {
            int index = 0;
            while (i != parent[i]) {
                help[index++] = i;
                i = parent[i];
            }
            for (index--; index >= 0; index--) {
                parent[help[index]] = i;
            }
            return i;
        }

        void union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx != fy) {
                if (size[fx] >= size[fy]) {
                    parent[fy] = fx;
                    if (flag[fx] && !flag[fy]) {
                        set -= size[fy];
                    } else if (!flag[fx] && flag[fy]) {
                        set -= size[fx];
                    }
                    size[fx] += size[fy];
                    flag[fx] |= flag[fy];
                } else {
                    parent[fx] = fy;
                    if (flag[fx] && !flag[fy]) {
                        set -= size[fy];
                    } else if (!flag[fx] && flag[fy]) {
                        set -= size[fx];
                    }
                    size[fy] += size[fx];
                    flag[fy] |= flag[fx];
                }
            }
        }

        int getSet() {
            return set;
        }

    }

    public int numEnclaves1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        UnionFind unionFind = new UnionFind(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int index = i * n + j;
                    if (j + 1 < n && grid[i][j + 1] == 1) {
                        unionFind.union(index, index + 1);
                    }
                    if (i + 1 < m && grid[i + 1][j] == 1) {
                        unionFind.union(index, index + n);
                    }
                }
            }
        }
        return unionFind.getSet();
    }

    public int numEnclaves2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, n - 1);
        }
        for (int j = 1; j < n - 1; j++) {
            dfs(grid, 0, j);
            dfs(grid, m - 1, j);
        }
        int res = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1) {
                    res++;
                }
            }
        }
        return res;
    }

    private void dfs(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1) {
            return;
        }
        grid[i][j] = 0;
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) {
                queue.offer(new int[]{i, 0});
            }
            if (grid[i][n - 1] == 1) {
                queue.offer(new int[]{i, n - 1});
            }
        }
        for (int j = 1; j < n - 1; j++) {
            if (grid[0][j] == 1) {
                queue.offer(new int[]{0, j});
            }
            if (grid[m - 1][j] == 1) {
                queue.offer(new int[]{m - 1, j});
            }
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            if (grid[x][y] == 1) {
                grid[x][y] = 0;
                for (int[] dir : dirs) {
                    int nx = x + dir[0], ny = y + dir[1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1) {
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
        int res = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1) {
                    res++;
                }
            }
        }
        return res;
    }

}
