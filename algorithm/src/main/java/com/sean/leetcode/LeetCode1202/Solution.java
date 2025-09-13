package com.sean.leetcode.LeetCode1202;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Author xionghaiyang
 * @Date 2025-09-13 19:41
 * @Description https://leetcode.cn/problems/smallest-string-with-swaps
 * 1202. 交换字符串中的元素
 * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
 * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
 * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s 中只含有小写英文字母
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

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        UnionFind unionFind = new UnionFind(n);
        for (List<Integer> pair : pairs) {
            unionFind.union(pair.get(0), pair.get(1));
        }
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int p = unionFind.find(i);
            map.computeIfAbsent(p, e -> new PriorityQueue<>()).offer(s.charAt(i));
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            PriorityQueue<Character> heap = map.get(unionFind.find(i));
            res.append(heap.poll());
        }
        return res.toString();
    }

}
