package com.sean.leetcode;

import java.util.*;

public class LeetCode928 {

    int[][] graph;
    int len;
    int[] clean;

    public int minMalwareSpread(int[][] graph, int[] initial) {
        this.graph = graph;
        this.len = graph.length;
        //构建一个集合,代表不在initial中的节点（值为1）
        this.clean = new int[len];
        Arrays.fill(clean, 1);
        for (int x : initial) {
            clean[x] = 0;
        }
        //intial中的节点x会感染哪些不在initial中的节点y
        //换句话说不在initial中的节点y会被哪些在initial中的节点x感染
        ArrayList<Integer>[] inflectBy = new ArrayList[len];
        for (int i = 0; i < len; i++) {
            inflectBy[i] = new ArrayList<>();
        }
        for (int x : initial) {
            Set<Integer> set = new HashSet<>();
            dfs(x, set);
            for (int y : set) {
                inflectBy[y].add(x);
            }
        }
        //哪些不在initial中的节点y只被一个在initial中的节点x感染(initial的节点x会感染几个节点)
        int[] contribution = new int[len];
        for (int y = 0; y < len; y++) {
            if (inflectBy[y].size() == 1) {
                contribution[inflectBy[y].get(0)]++;
            }
        }
        Arrays.sort(initial);
        int res = initial[0];
        int resSize = -1;
        for (int x : initial) {
            int score = contribution[x];
            if (score > resSize) {
                res = x;
                resSize = score;
            }
        }
        return res;
    }

    private void dfs(int x, Set<Integer> set) {
        for (int y = 0; y < len; y++) {
            if (graph[x][y] == 1 && clean[y] == 1 && !set.contains(y)) {
                set.add(y);
                dfs(y, set);
            }
        }
    }

}






