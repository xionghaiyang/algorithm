package com.sean.leetcode.LeetCode3532;

/**
 * @Author: xionghaiyang
 * @Date: 2026-07-09 07:22
 * @Description: https://leetcode.cn/problems/path-existence-queries-in-a-graph-i
 * 3532. 针对图的路径存在性查询 I
 * 给你一个整数 n，表示图中的节点数量，这些节点按从 0 到 n - 1 编号。
 * 同时给你一个长度为 n 的整数数组 nums，该数组按 非递减 顺序排序，以及一个整数 maxDiff。
 * 如果满足 |nums[i] - nums[j]| <= maxDiff（即 nums[i] 和 nums[j] 的 绝对差 至多为 maxDiff），则节点 i 和节点 j 之间存在一条 无向边 。
 * 此外，给你一个二维整数数组 queries。
 * 对于每个 queries[i] = [ui, vi]，需要判断节点 ui 和 vi 之间是否存在路径。
 * 返回一个布尔数组 answer，其中 answer[i] 等于 true 表示在第 i 个查询中节点 ui 和 vi 之间存在路径，否则为 false。
 * 1 <= n == nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 * nums 按 非递减 顺序排序。
 * 0 <= maxDiff <= 10^5
 * 1 <= queries.length <= 10^5
 * queries[i] == [ui, vi]
 * 0 <= ui, vi < n
 */
public class Solution {

    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] p = new int[n];
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                p[i] = p[i - 1] + 1;
            } else {
                p[i] = p[i - 1];
            }
        }
        int m = queries.length;
        boolean[] res = new boolean[m];
        for (int i = 0; i < m; i++) {
            int u = queries[i][0], v = queries[i][1];
            res[i] = p[u] == p[v];
        }
        return res;
    }

}
