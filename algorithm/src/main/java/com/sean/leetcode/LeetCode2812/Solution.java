package com.sean.leetcode.LeetCode2812;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-07-01 07:49
 * @Description: https://leetcode.cn/problems/find-the-safest-path-in-a-grid
 * 2812. 找出最安全路径
 * 给你一个下标从 0 开始、大小为 n x n 的二维矩阵 grid ，其中 (r, c) 表示：
 * 如果 grid[r][c] = 1 ，则表示一个存在小偷的单元格
 * 如果 grid[r][c] = 0 ，则表示一个空单元格
 * 你最开始位于单元格 (0, 0) 。
 * 在一步移动中，你可以移动到矩阵中的任一相邻单元格，包括存在小偷的单元格。
 * 矩阵中路径的 安全系数 定义为：从路径中任一单元格到矩阵中任一小偷所在单元格的 最小 曼哈顿距离。
 * 返回所有通向单元格 (n - 1, n - 1) 的路径中的 最大安全系数 。
 * 单元格 (r, c) 的某个 相邻 单元格，是指在矩阵中存在的 (r, c + 1)、(r, c - 1)、(r + 1, c) 和 (r - 1, c) 之一。
 * 两个单元格 (a, b) 和 (x, y) 之间的 曼哈顿距离 等于 | a - x | + | b - y | ，其中 |val| 表示 val 的绝对值。
 * 1 <= grid.length == n <= 400
 * grid[i].length == n
 * grid[i][j] 为 0 或 1
 * grid 至少存在一个小偷
 */
public class Solution {

    public class UnionFind {
        private int n;
        private int[] parent;
        private int[] size;
        private int[] help;

        public UnionFind(int n) {
            this.n = n;
            parent = new int[n * n];
            size = new int[n * n];
            help = new int[n * n];
            for (int i = 0; i < n * n; i++) {
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
                size[fx] += size[fy];
                parent[fx] = fy;
            } else {
                size[fy] += size[fx];
                parent[fy] = fx;
            }
        }

        public boolean isSameSet(int x, int y) {
            return find(x) == find(y);
        }
    }

    private final static int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] dis = new int[n][n];
        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    q.add(new int[]{i, j});
                } else {
                    dis[i][j] = -1;
                }
            }
        }
        List<List<int[]>> groups = new ArrayList<>();
        groups.add(q);
        while (!q.isEmpty()) {
            List<int[]> tmp = q;
            q = new ArrayList<>();
            for (int[] p : tmp) {
                for (int[] dir : DIRS) {
                    int x = p[0] + dir[0], y = p[1] + dir[1];
                    if (0 <= x && x < n && 0 <= y && y < n && dis[x][y] < 0) {
                        dis[x][y] = groups.size();
                        q.add(new int[]{x, y});
                    }
                }
            }
            groups.add(q);
        }
        UnionFind unionFind = new UnionFind(n);
        for (int res = groups.size() - 2; res > 0; res--) {
            for (int[] p : groups.get(res)) {
                int i = p[0], j = p[1];
                for (int[] dir : DIRS) {
                    int x = i + dir[0], y = j + dir[1];
                    if (0 <= x && x < n && 0 <= y && y < n && dis[x][y] >= res) {
                        unionFind.union(x * n + y, i * n + j);
                    }
                }
            }
            if (unionFind.isSameSet(0, n * n - 1)) {
                return res;
            }
        }
        return 0;
    }

}
