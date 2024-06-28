package com.sean.leetcode.LeetCode547;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-17 10:55
 * @Description: https://leetcode.cn/problems/number-of-provinces/?plan=graph&plan_progress=zq56763
 * 547. 省份数量
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。
 * 如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，
 * 而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 */
public class Solution {

    class UnionFind {
        int[] parent;
        int[] size;
        int[] help;
        int set;

        UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            set = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
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

        void union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx != fy) {
                if (size[fx] >= size[fy]) {
                    parent[fy] = fx;
                    size[fx] += size[fy];
                    set--;
                } else {
                    parent[fx] = fy;
                    size[fy] += size[fx];
                    set--;
                }
            }
        }

        int getSet() {
            return set;
        }

    }

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.getSet();
    }

}
