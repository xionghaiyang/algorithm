package com.sean.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2022-10-16 22:36
 * @Description https://leetcode.cn/problems/possible-bipartition/
 * 886. 可能的二分法
 * 给定一组 n 人（编号为 1, 2, ..., n）， 我们想把每个人分进任意大小的两组。
 * 每个人都可能不喜欢其他人，那么他们不应该属于同一组。
 * 给定整数 n 和数组 dislikes ，其中 dislikes[i] = [ai, bi] ，表示不允许将编号为 ai 和  bi的人归入同一组。
 * 当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。
 */
public class LeetCode886 {

    public class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;

        public UnionFind(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            help = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i) {
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

        public boolean isSameSet(int i, int j) {
            return find(i) == find(j);
        }
    }

    public boolean possibleBipartition(int n, int[][] dislikes) {
        UnionFind unionFind = new UnionFind(n);
        List<Integer>[] list = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int[] dislike : dislikes) {
            list[dislike[0]].add(dislike[1]);
            list[dislike[1]].add(dislike[0]);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < list[i].size(); j++) {
                unionFind.union(list[i].get(0), list[i].get(j));
            }
        }
        for (int[] dislike : dislikes) {
            if (unionFind.isSameSet(dislike[0], dislike[1])) {
                return false;
            }
        }
        return true;
    }

}
