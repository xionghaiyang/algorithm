package com.sean.leetcode.LeetCode1319;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-17 11:15
 * @Description: https://leetcode.cn/problems/number-of-operations-to-make-network-connected/?plan=graph&plan_progress=zq56763
 * 1319. 连通网络的操作次数
 * 用以太网线缆将 n 台计算机连接成一个网络，计算机的编号从 0 到 n-1。
 * 线缆用 connections 表示，其中 connections[i] = [a, b] 连接了计算机 a 和 b。
 * 网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。
 * 给你这个计算机网络的初始布线 connections，你可以拔开任意两台直连计算机之间的线缆，
 * 并用它连接一对未直连的计算机。
 * 请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回 -1 。 
 */
public class Solution {

    class UnionFind {
        int[] parent;
        int[] size;
        int[] help;
        int set;
        int rest;

        UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            set = n;
            rest = 0;
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
            } else {
                rest++;
            }
        }

        int getSet() {
            return set;
        }

        int getRest() {
            return rest;
        }

    }

    public int makeConnected(int n, int[][] connections) {
        UnionFind unionFind = new UnionFind(n);
        for (int[] connection : connections) {
            unionFind.union(connection[0], connection[1]);
        }
        int set = unionFind.getSet();
        int rest = unionFind.getRest();
        if (set == 1) {
            return 0;
        } else {
            return rest >= set - 1 ? set - 1 : -1;
        }
    }

}
