package com.sean.leetcode.LeetCode1697;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-14 08:45
 * @Description: https://leetcode.cn/problems/checking-existence-of-edge-length-limited-paths/
 * 1697. 检查边长度限制的路径是否存在
 * 给你一个 n 个点组成的无向图边集 edgeList ，其中 edgeList[i] = [ui, vi, disi] 表示点 ui 和点 vi 之间有一条长度为 disi 的边。
 * 请注意，两个点之间可能有 超过一条边 。
 * 给你一个查询数组queries ，其中 queries[j] = [pj, qj, limitj] ，你的任务是对于每个查询 queries[j] 
 * ，判断是否存在从 pj 到 qj 的路径，且这条路径上的每一条边都 严格小于 limitj 。
 * 请你返回一个 布尔数组 answer ，其中 answer.length == queries.length ，
 * 当 queries[j] 的查询结果为 true 时， answer 第 j 个值为 true ，否则为 false 。
 */
public class Solution {

    private class UnionFind {
        int[] parent;
        int[] size;
        int[] help;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int i, int j) {
            int fi = find(i);
            int fj = find(j);
            if (fi != fj) {
                if (size[fi] >= size[fj]) {
                    size[fi] += size[fj];
                    parent[fj] = fi;
                } else {
                    size[fj] += size[fi];
                    parent[fi] = fj;
                }
            }
        }

        public int find(int i) {
            int t = 0;
            while (i != parent[i]) {
                help[t++] = i;
                i = parent[i];
            }
            for (t--; t >= 0; t--) {
                parent[help[t]] = i;
            }
            return i;
        }

        public boolean isSameSet(int i, int j) {
            return find(i) == find(j);
        }

    }

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
        Integer[] index = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++) {
            index[i] = i;
        }
        Arrays.sort(index, (a, b) -> queries[a][2] - queries[b][2]);
        UnionFind unionFind = new UnionFind(n);
        boolean[] res = new boolean[queries.length];
        int j = 0;
        for (int i : index) {
            while (j < edgeList.length && edgeList[j][2] < queries[i][2]) {
                unionFind.union(edgeList[j][0], edgeList[j][1]);
                j++;
            }
            res[i] = unionFind.isSameSet(queries[i][0], queries[i][1]);
        }
        return res;
    }

}
