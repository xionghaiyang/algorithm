package com.sean.leetcode.LeetCode2612;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author xionghaiyang
 * @Date 2025-03-20 09:11
 * @Description https://leetcode.cn/problems/minimum-reverse-operations
 * 2612. 最少翻转操作数
 * 给你一个整数 n 和一个在范围 [0, n - 1] 以内的整数 p ，它们表示一个长度为 n 且下标从 0 开始的数组 arr ，
 * 数组中除了下标为 p 处是 1 以外，其他所有数都是 0 。
 * 同时给你一个整数数组 banned ，它包含数组中的一些位置。
 * banned 中第 i 个位置表示 arr[banned[i]] = 0 ，题目保证 banned[i] != p 。
 * 你可以对 arr 进行 若干次 操作。
 * 一次操作中，你选择大小为 k 的一个 子数组 ，并将它 翻转 。
 * 在任何一次翻转操作后，你都需要确保 arr 中唯一的 1 不会到达任何 banned 中的位置。
 * 换句话说，arr[banned[i]] 始终 保持 0 。
 * 请你返回一个数组 ans ，对于 [0, n - 1] 之间的任意下标 i ，ans[i] 是将 1 放到位置 i 处的 最少 翻转操作次数，如果无法放到位置 i 处，此数为 -1 。
 * 子数组 指的是一个数组里一段连续 非空 的元素序列。
 * 对于所有的 i ，ans[i] 相互之间独立计算。
 * 将一个数组中的元素 翻转 指的是将数组中的值变成 相反顺序 。
 * 1 <= n <= 10^5
 * 0 <= p <= n - 1
 * 0 <= banned.length <= n - 1
 * 0 <= banned[i] <= n - 1
 * 1 <= k <= n
 * banned[i] != p
 * banned 中的值 互不相同 。
 */
public class Solution {

    class UnionFind {
        private final int[] arr;

        public UnionFind(int n) {
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = i;
            }
        }

        public int find(int x) {
            if (arr[x] != x) {
                arr[x] = find(arr[x]);
            }
            return arr[x];
        }

        public void merge(int from, int to) {
            arr[find(from)] = find(to);
        }
    }

    public int[] minReverseOperations(int n, int p, int[] banned, int k) {
        UnionFind unionFind = new UnionFind(n + 2);
        unionFind.merge(p, p + 2);
        for (int i : banned) {
            unionFind.merge(i, i + 2);
        }
        int[] res = new int[n];
        Arrays.fill(res, -1);
        res[p] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(p);
        while (!queue.isEmpty()) {
            int i = queue.poll();
            int mn = Math.max(i - k + 1, k - i - 1);
            int mx = Math.min(i + k - 1, n * 2 - k - i - 1);
            for (int j = unionFind.find(mn); j <= mx; j = unionFind.find(j + 2)) {
                res[j] = res[i] + 1;
                queue.offer(j);
                unionFind.merge(j, mx + 2);
            }
        }
        return res;
    }

}
