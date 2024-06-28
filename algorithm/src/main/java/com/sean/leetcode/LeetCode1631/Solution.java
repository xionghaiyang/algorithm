package com.sean.leetcode.LeetCode1631;

import java.util.*;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-11 16:14
 * @Description: https://leetcode.cn/problems/path-with-minimum-effort/
 * 1631. 最小体力消耗路径
 * 你准备参加一场远足活动。
 * 给你一个二维 rows x columns 的地图 heights ，
 * 其中 heights[row][col] 表示格子 (row, col) 的高度。
 * 一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。
 * 你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
 * 请你返回从左上角走到右下角的最小 体力消耗值 。
 */
public class Solution {

    public int minimumEffortPath(int[][] heights) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = heights.length;
        int n = heights[0].length;
        boolean[] seen = new boolean[m * n];
        int[] dist = new int[m * n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[]{0, 0, 0});
        dist[0] = 0;
        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int x = edge[0];
            int y = edge[1];
            int d = edge[2];
            int id = x * n + y;
            if (seen[id]) {
                continue;
            }
            if (x == m - 1 && y == n - 1) {
                break;
            }
            seen[x * n + y] = true;
            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && Math.max(d, Math.abs(heights[x][y] - heights[nx][ny])) < dist[nx * n + ny]) {
                    dist[nx * n + ny] = Math.max(d, Math.abs(heights[x][y] - heights[nx][ny]));
                    pq.offer(new int[]{nx, ny, dist[nx * n + ny]});
                }
            }
        }
        return dist[m * n - 1];
    }

    public int minimumEffortPath1(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int id = i * n + j;
                if (i > 0) {
                    edges.add(new int[]{id - n, id, Math.abs(heights[i][j] - heights[i - 1][j])});
                }
                if (j > 0) {
                    edges.add(new int[]{id - 1, id, Math.abs(heights[i][j] - heights[i][j - 1])});
                }
            }
        }
        Collections.sort(edges, (a, b) -> a[2] - b[2]);
        UnionFind unionFind = new UnionFind(m * n);
        int res = 0;
        for (int[] edge : edges) {
            unionFind.union(edge[0], edge[1]);
            if (unionFind.find(0) == unionFind.find(m * n - 1)) {
                res = edge[2];
                break;
            }
        }
        return res;
    }

    public class UnionFind {
        //parent[i]=k:i的父亲是k
        private int[] parent;
        //size[i]=k:如果i是代表节点,size[i]才有意义，否则无意义
        //i所在集合的大小是多少
        private int[] size;
        //辅助结构
        private int[] help;
        //一共有多少个集合
        private int sets;

        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        //从i开始一直往上，往上到不能再往上，代表节点返回
        //这个过程要做路径压缩
        private int find(int i) {
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

        private void union(int i, int j) {
            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = parent[f1];
                } else {
                    size[f2] += size[f1];
                    parent[f1] = parent[f2];
                }
                sets--;
            }
        }

        public int sets() {
            return sets;
        }
    }

}
