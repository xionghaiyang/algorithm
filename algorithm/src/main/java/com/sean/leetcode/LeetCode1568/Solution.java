package com.sean.leetcode.LeetCode1568;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-09-19 20:02
 * @Description https://leetcode.cn/problems/minimum-number-of-days-to-disconnect-island
 * 1568. 使陆地分离的最少天数
 * 给你一个大小为 m x n ，由若干 0 和 1 组成的二维网格 grid ，其中 1 表示陆地， 0 表示水。
 * 岛屿 由水平方向或竖直方向上相邻的 1 （陆地）连接形成。
 * 如果 恰好只有一座岛屿 ，则认为陆地是 连通的 ；否则，陆地就是 分离的 。
 * 一天内，可以将 任何单个 陆地单元（1）更改为水单元（0）。
 * 返回使陆地分离的最少天数。
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 30
 * grid[i][j] 为 0 或 1
 */
public class Solution {

    private int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int minDays(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int landCount = 0;
        int[] label = new int[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    label[i * n + j] = landCount++;
                }
            }
        }
        if (landCount == 0) {
            return 0;
        } else if (landCount == 1) {
            return 1;
        }
        List<Integer>[] g = new ArrayList[landCount];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int[] dir : dirs) {
                        int x = i + dir[0], y = j + dir[1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                            g[label[i * n + j]].add(label[x * n + y]);
                        }
                    }
                }
            }
        }
        TarjanSCC scc = new TarjanSCC(g);
        return scc.check();
    }

    public class TarjanSCC {
        private List<Integer>[] g;
        private int n;
        private int[] low;
        private int[] dfn;
        private int[] parent;
        private int timestamp;

        public TarjanSCC(List<Integer>[] g) {
            this.g = g;
            n = g.length;
            low = new int[n];
            dfn = new int[n];
            parent = new int[n];
            timestamp = -1;
            Arrays.fill(low, -1);
            Arrays.fill(dfn, -1);
            Arrays.fill(parent, -1);
        }

        public int check() {
            List<Integer> cvs = new ArrayList<>();
            int cccnt = 0;
            for (int i = 0; i < n; i++) {
                if (dfn[i] == -1) {
                    cccnt++;
                    tarjan(i, -1, cvs);
                }
            }
            //如果连通分量个数大于1
            if (cccnt > 1) {
                return 0;
            }
            //如果存在割点
            if (!cvs.isEmpty()) {
                return 1;
            }
            return 2;
        }

        private void tarjan(int u, int p, List<Integer> cvs) {
            low[u] = dfn[u] = ++timestamp;
            parent[u] = p;
            int child = 0;
            boolean isCv = false;
            for (int v : g[u]) {
                if (dfn[v] == -1) {
                    child++;
                    tarjan(v, u, cvs);
                    low[u] = Math.min(low[u], low[v]);
                    if (!isCv && p != -1 && low[v] >= dfn[u]) {
                        cvs.add(u);
                        isCv = true;
                    }
                } else if (v != parent[u]) {
                    low[u] = Math.min(low[u], dfn[v]);
                }
            }
            if (!isCv && p == -1 && child >= 2) {
                cvs.add(u);
            }
        }
    }

}
