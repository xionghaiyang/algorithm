package com.sean.leetcode.LeetCode1559;

/**
 * @Author xionghaiyang
 * @Date 2025-08-29 19:37
 * @Description https://leetcode.cn/problems/detect-cycles-in-2d-grid
 * 1559. 二维网格图中探测环
 * 给你一个二维字符网格数组 grid ，大小为 m x n ，你需要检查 grid 中是否存在 相同值 形成的环。
 * 一个环是一条开始和结束于同一个格子的长度 大于等于 4 的路径。
 * 对于一个给定的格子，你可以移动到它上、下、左、右四个方向相邻的格子之一，可以移动的前提是这两个格子有 相同的值 。
 * 同时，你也不能回到上一次移动时所在的格子。
 * 比方说，环  (1, 1) -> (1, 2) -> (1, 1) 是不合法的，因为从 (1, 2) 移动到 (1, 1) 回到了上一次移动时的格子。
 * 如果 grid 中有相同值形成的环，请你返回 true ，否则返回 false 。
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 500
 * 1 <= n <= 500
 * grid 只包含小写英文字母。
 */
public class Solution {

    public class UnionFind {
        private int[] parent;
        private int[] help;
        private int[] size;

        public UnionFind(int n) {
            parent = new int[n];
            help = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
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

        public boolean union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx == fy) {
                return false;
            }
            if (size[fx] >= size[fy]) {
                parent[fy] = fx;
                size[fx] += size[fy];
            } else {
                parent[fx] = fy;
                size[fy] += size[fx];
            }
            return true;
        }
    }

    public boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        UnionFind unionFind = new UnionFind(m * n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && grid[i][j] == grid[i - 1][j]) {
                    if (!unionFind.union(i * n + j, (i - 1) * n + j)) {
                        return true;
                    }
                }
                if (j > 0 && grid[i][j] == grid[i][j - 1]) {
                    if (!unionFind.union(i * n + j, i * n + j - 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
