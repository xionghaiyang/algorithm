package com.sean.leetcode.LeetCode959;

/**
 * @Author xionghaiyang
 * @Date 2025-09-10 19:55
 * @Description https://leetcode.cn/problems/regions-cut-by-slashes
 * 959. 由斜杠划分区域
 * 在由 1 x 1 方格组成的 n x n 网格 grid 中，每个 1 x 1 方块由 '/'、'\' 或空格构成。
 * 这些字符会将方块划分为一些共边的区域。
 * 给定网格 grid 表示为一个字符串数组，返回 区域的数量 。
 * 请注意，反斜杠字符是转义的，因此 '\' 用 '\\' 表示。
 * n == grid.length == grid[i].length
 * 1 <= n <= 30
 * grid[i][j] 是 '/'、'\'、或 ' '
 */
public class Solution {

    public class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int set;

        public UnionFind(int n) {
            parent = new int[n * n * 4];
            size = new int[n * n * 4];
            help = new int[n * n * 4];
            set = n * n * 4;
            for (int i = 0; i < n * n * 4; i++) {
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

        public void union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx == fy) {
                return;
            }
            if (size[fx] >= size[fy]) {
                parent[fy] = fx;
                size[fx] += size[fy];
            } else {
                parent[fx] = fy;
                size[fy] += size[fx];
            }
            set--;
        }

        public int getSet() {
            return set;
        }
    }

    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int index = i * n + j;
                if (i < n - 1) {
                    int bottom = index + n;
                    unionFind.union(index * 4 + 2, bottom * 4);
                }
                if (j < n - 1) {
                    int right = index + 1;
                    unionFind.union(index * 4 + 1, right * 4 + 3);
                }
                if (grid[i].charAt(j) == '/') {
                    unionFind.union(index * 4, index * 4 + 3);
                    unionFind.union(index * 4 + 1, index * 4 + 2);
                } else if (grid[i].charAt(j) == '\\') {
                    unionFind.union(index * 4, index * 4 + 1);
                    unionFind.union(index * 4 + 2, index * 4 + 3);
                } else {
                    unionFind.union(index * 4, index * 4 + 1);
                    unionFind.union(index * 4 + 1, index * 4 + 2);
                    unionFind.union(index * 4 + 2, index * 4 + 3);
                }
            }
        }
        return unionFind.getSet();
    }

}
