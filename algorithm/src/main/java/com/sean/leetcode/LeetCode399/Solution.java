package com.sean.leetcode.LeetCode399;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-06-24 12:00
 * @Description https://leetcode.cn/problems/evaluate-division
 * 399. 除法求值
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。
 * 每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * 返回 所有问题的答案 。
 * 如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
 * 如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
 * 注意：输入总是有效的。
 * 你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 * 注意：未在等式列表中出现的变量是未定义的，因此无法确定它们的答案。
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj 由小写英文字母与数字组成
 */
public class Solution {

    public class UnionFind {
        private int[] parent;
        private double[] weights;

        public UnionFind(int n) {
            parent = new int[n];
            weights = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weights[i] = 1.0;
            }
        }

        public void union(int x, int y, double weight) {
            int fx = find(x);
            int fy = find(y);
            if (fx == fy) {
                return;
            }
            parent[fx] = fy;
            weights[fx] = weights[y] * weight / weights[x];
        }

        public int find(int x) {
            if (x != parent[x]) {
                int origin = parent[x];
                parent[x] = find(parent[x]);
                weights[x] *= weights[origin];
            }
            return parent[x];
        }

        public double isConnected(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx == fy) {
                return weights[x] / weights[y];
            } else {
                return -1.0;
            }
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int m = equations.size();
        UnionFind unionFind = new UnionFind(2 * m);
        Map<String, Integer> map = new HashMap<>();
        int index = 0;
        for (int i = 0; i < m; i++) {
            List<String> equation = equations.get(i);
            String A = equation.get(0);
            String B = equation.get(1);
            if (!map.containsKey(A)) {
                map.put(A, index++);
            }
            if (!map.containsKey(B)) {
                map.put(B, index++);
            }
            double weight = values[i];
            unionFind.union(map.get(A), map.get(B), weight);
        }
        int n = queries.size();
        double[] res = new double[n];
        for (int i = 0; i < n; i++) {
            List<String> query = queries.get(i);
            String C = query.get(0);
            String D = query.get(1);
            if (!map.containsKey(C) || !map.containsKey(D)) {
                res[i] = -1.0;
            } else {
                res[i] = unionFind.isConnected(map.get(C), map.get(D));
            }
        }
        return res;
    }

}
