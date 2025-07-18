package com.sean.leetcode.LeetCode200;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-08 19:35
 * @Description: https://leetcode.cn/problems/number-of-islands
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 */
public class Solution {

    //深度优先搜索
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

    //广度优先搜索
    public int numIslands1(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    grid[i][j] = '0';
                    Queue<Integer> queue = new LinkedList<>();
                    queue.offer(i * n + j);
                    while (!queue.isEmpty()) {
                        int index = queue.poll();
                        int row = index / n;
                        int col = index % n;
                        if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                            queue.offer((row - 1) * n + col);
                            grid[row - 1][col] = '0';
                        }
                        if (row + 1 < m && grid[row + 1][col] == '1') {
                            queue.offer((row + 1) * n + col);
                            grid[row + 1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                            queue.offer(row * n + col - 1);
                            grid[row][col - 1] = '0';
                        }
                        if (col + 1 < n && grid[row][col + 1] == '1') {
                            queue.offer(row * n + col + 1);
                            grid[row][col + 1] = '0';
                        }
                    }
                }
            }
        }
        return res;
    }

    public class UnionFind {
        private int m;
        private int n;
        private int[] parent;
        private int[] help;
        private int[] size;
        private int set;

        public UnionFind(char[][] grid) {
            m = grid.length;
            n = grid[0].length;
            parent = new int[m * n];
            help = new int[m * n];
            size = new int[m * n];
            set = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int index = getIndex(i, j);
                    if (grid[i][j] == '1') {
                        parent[index] = index;
                        set++;
                    }
                    size[index] = 1;
                }
            }
        }

        private int getIndex(int i, int j) {
            return i * n + j;
        }

        public int find(int x) {
            int index = 0;
            while (x != parent[x]) {
                help[index++] = x;
                x = parent[x];
            }
            for (index--; index >= 0; index--) {
                parent[help[index]] = x;
            }
            return x;
        }

        public void union(int xi, int xj, int yi, int yj) {
            int fx = find(getIndex(xi, xj));
            int fy = find(getIndex(yi, yj));
            if (fx == fy) {
                return;
            }
            if (size[fx] >= size[fy]) {
                size[fx] += size[fy];
                parent[fy] = fx;
            } else {
                size[fy] += size[fx];
                parent[fx] = fy;
            }
            set--;
        }

        public int getSet() {
            return set;
        }
    }

    //并查集
    public int numIslands2(char[][] grid) {
        UnionFind unionFind = new UnionFind(grid);
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                        unionFind.union(i, j, i - 1, j);
                    }
                    if (i + 1 < m && grid[i + 1][j] == '1') {
                        unionFind.union(i, j, i + 1, j);
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                        unionFind.union(i, j, i, j - 1);
                    }
                    if (j + 1 < n && grid[i][j + 1] == '1') {
                        unionFind.union(i, j, i, j + 1);
                    }
                }
            }
        }
        return unionFind.getSet();
    }

}
