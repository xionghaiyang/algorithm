package com.sean.leetcode.LeetCode1782;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-08-23 10:35
 * @Description: https://leetcode.cn/problems/count-pairs-of-nodes/
 * 1782. 统计点对的数目
 * 给你一个无向图，无向图由整数 n  ，表示图中节点的数目，和 edges 组成
 * ，其中 edges[i] = [ui, vi] 表示 ui 和 vi 之间有一条无向边。
 * 同时给你一个代表查询的整数数组 queries 。
 * 第 j 个查询的答案是满足如下条件的点对 (a, b) 的数目：
 * a < b
 * cnt 是与 a 或者 b 相连的边的数目，且 cnt 严格大于 queries[j] 。
 * 请你返回一个数组 answers ，其中 answers.length == queries.length 且 answers[j] 是第 j 个查询的答案。
 * 请注意，图中可能会有 重复边 。
 */
public class Solution {

    public int[] countPairs(int n, int[][] edges, int[] queries) {
        int[] degree = new int[n];
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int[] edge : edges) {
            int x = edge[0] - 1;
            int y = edge[1] - 1;
            if (x > y) {
                int temp = x;
                x = y;
                y = temp;
            }
            degree[x]++;
            degree[y]++;
            cnt.put(x * n + y, cnt.getOrDefault(x * n + y, 0) + 1);
        }
        int[] arr = Arrays.copyOf(degree, n);
        int m = queries.length;
        int[] res = new int[m];
        Arrays.sort(arr);
        for (int k = 0; k < m; k++) {
            int bound = queries[k];
            int total = 0;
            for (int i = 0; i < n; i++) {
                int j = binarySearch(arr, i + 1, n - 1, bound - arr[i]);
                total += n - j;
            }
            for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
                int val = entry.getKey();
                int freq = entry.getValue();
                int x = val / n;
                int y = val % n;
                if (degree[x] + degree[y] > bound && degree[x] + degree[y] - freq <= bound) {
                    total--;
                }
            }
            res[k] = total;
        }
        return res;
    }

    private int binarySearch(int[] arr, int left, int right, int target) {
        int res = right + 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
                res = mid;
            }
        }
        return res;
    }

}
