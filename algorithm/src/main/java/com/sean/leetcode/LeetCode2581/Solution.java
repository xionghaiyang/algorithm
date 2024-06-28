package com.sean.leetcode.LeetCode2581;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-02-29 11:23
 * @Description: https://leetcode.cn/problems/count-number-of-possible-root-nodes/
 * 2581. 统计可能的树根数目
 * Alice 有一棵 n 个节点的树，节点编号为 0 到 n - 1 。
 * 树用一个长度为 n - 1 的二维整数数组 edges 表示，其中 edges[i] = [ai, bi] ，表示树中节点 ai 和 bi 之间有一条边。
 * Alice 想要 Bob 找到这棵树的根。
 * 她允许 Bob 对这棵树进行若干次 猜测 。
 * 每一次猜测，Bob 做如下事情：
 * 选择两个 不相等 的整数 u 和 v ，且树中必须存在边 [u, v] 。
 * Bob 猜测树中 u 是 v 的 父节点 。
 * Bob 的猜测用二维整数数组 guesses 表示，其中 guesses[j] = [uj, vj] 表示 Bob 猜 uj 是 vj 的父节点。
 * Alice 非常懒，她不想逐个回答 Bob 的猜测，只告诉 Bob 这些猜测里面 至少 有 k 个猜测的结果为 true 。
 * 给你二维整数数组 edges ，Bob 的所有猜测和整数 k ，请你返回可能成为树根的 节点数目 。
 * 如果没有这样的树，则返回 0。
 */
public class Solution {

    int k;
    List<Integer>[] g;
    Set<Long> set;
    int cnt = 0;
    int res = 0;

    public int rootCount(int[][] edges, int[][] guesses, int k) {
        this.k = k;
        int n = edges.length + 1;
        g = new List[n];
        set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }
        for (int[] guess : guesses) {
            set.add(hash(guess[0], guess[1]));
        }
        dfs(0, -1);
        redfs(0, -1, cnt);
        return res;
    }

    private long hash(int x, int y) {
        return (long) x << 32 | y;
    }

    private void dfs(int x, int parent) {
        for (int y : g[x]) {
            if (y == parent) {
                continue;
            }
            if (set.contains(hash(x, y))) {
                cnt++;
            }
            dfs(y, x);
        }
    }

    private void redfs(int x, int parent, int cnt) {
        if (cnt >= k) {//此时cnt就是以x为根时的猜对次数
            res++;
        }
        for (int y : g[x]) {
            if (y == parent) {
                continue;
            }
            int c = cnt;
            if (set.contains(hash(x, y))) {
                c--;//原来是对的，现在错了
            }
            if (set.contains(hash(y, x))) {
                c++;//原来是错的，现在对了
            }
            redfs(y, x, c);
        }
    }

}
