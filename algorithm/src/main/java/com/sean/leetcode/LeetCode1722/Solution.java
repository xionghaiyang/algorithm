package com.sean.leetcode.LeetCode1722;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-09-23 21:00
 * @Description https://leetcode.cn/problems/minimize-hamming-distance-after-swap-operations
 * 1722. 执行交换操作后的最小汉明距离
 * 给你两个整数数组 source 和 target ，长度都是 n 。
 * 还有一个数组 allowedSwaps ，其中每个 allowedSwaps[i] = [ai, bi] 表示你可以交换数组 source 中下标为 ai 和 bi（下标从 0 开始）的两个元素。
 * 注意，你可以按 任意 顺序 多次 交换一对特定下标指向的元素。
 * 相同长度的两个数组 source 和 target 间的 汉明距离 是元素不同的下标数量。
 * 形式上，其值等于满足 source[i] != target[i] （下标从 0 开始）的下标 i（0 <= i <= n-1）的数量。
 * 在对数组 source 执行 任意 数量的交换操作后，返回 source 和 target 间的 最小汉明距离 。
 * n == source.length == target.length
 * 1 <= n <= 10^5
 * 1 <= source[i], target[i] <= 10^5
 * 0 <= allowedSwaps.length <= 10^5
 * allowedSwaps[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 */
public class Solution {

    public class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
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
        }
    }

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        UnionFind unionFind = new UnionFind(n);
        for (int[] allowedSwap : allowedSwaps) {
            unionFind.union(allowedSwap[0], allowedSwap[1]);
        }
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int group = unionFind.find(i);
            map.putIfAbsent(group, new HashMap<>());
            Map<Integer, Integer> cnt = map.get(group);
            cnt.put(target[i], cnt.getOrDefault(target[i], 0) + 1);
        }
        int res = n;
        for (int i = 0; i < n; i++) {
            int group = unionFind.find(i);
            Map<Integer, Integer> cnt = map.get(group);
            if (cnt.getOrDefault(source[i], 0) > 0) {
                res--;
                cnt.put(source[i], cnt.get(source[i]) - 1);
            }
        }
        return res;
    }

}
