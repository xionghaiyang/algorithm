package com.sean.leetcode.LeetCode3534;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-07-10 06:14
 * @Description: https://leetcode.cn/problems/path-existence-queries-in-a-graph-ii
 * 3534. 针对图的路径存在性查询 II
 * 给你一个整数 n，表示图中的节点数量，这些节点按从 0 到 n - 1 编号。
 * 同时给你一个长度为 n 的整数数组 nums，以及一个整数 maxDiff。
 * 如果满足 |nums[i] - nums[j]| <= maxDiff（即 nums[i] 和 nums[j] 的 绝对差 至多为 maxDiff），则节点 i 和节点 j 之间存在一条 无向边 。
 * 此外，给你一个二维整数数组 queries。
 * 对于每个 queries[i] = [ui, vi]，找到节点 ui 和节点 vi 之间的 最短距离 。
 * 如果两节点之间不存在路径，则返回 -1。
 * 返回一个数组 answer，其中 answer[i] 是第 i 个查询的结果。
 * 注意：节点之间的边是无权重（unweighted）的。
 * 1 <= n == nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 * 0 <= maxDiff <= 10^5
 * 1 <= queries.length <= 10^5
 * queries[i] == [ui, vi]
 * 0 <= ui, vi < n
 */
public class Solution {

    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        Integer[] index = new Integer[n];
        Arrays.setAll(index, i -> i);
        Arrays.sort(index, (i, j) -> nums[i] - nums[j]);
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[index[i]] = i;
        }
        int max = 32 - Integer.numberOfLeadingZeros(n);
        int[][] p = new int[n][max];
        for (int i = 0, left = 0; i < n; i++) {
            while (nums[index[i]] - nums[index[left]] > maxDiff) {
                left++;
            }
            p[i][0] = left;
        }
        for (int i = 0; i < max - 1; i++) {
            for (int x = 0; x < n; x++) {
                p[x][i + 1] = p[p[x][i]][i];
            }
        }
        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int left = queries[i][0], right = queries[i][1];
            if (left == right) {
                continue;
            }
            left = rank[left];
            right = rank[right];
            if (left > right) {
                int temp = left;
                left = right;
                right = temp;
            }
            int ans = 0;
            for (int k = max - 1; k >= 0; k--) {
                if (p[right][k] > left) {
                    ans |= 1 << k;
                    right = p[right][k];
                }
            }
            res[i] = p[right][0] > left ? -1 : ans + 1;
        }
        return res;
    }

}
