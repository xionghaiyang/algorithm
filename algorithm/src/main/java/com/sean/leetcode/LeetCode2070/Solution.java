package com.sean.leetcode.LeetCode2070;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-03-09 08:52
 * @Description https://leetcode.cn/problems/most-beautiful-item-for-each-query
 * 2070. 每一个查询的最大美丽值
 * 给你一个二维整数数组 items ，其中 items[i] = [pricei, beautyi] 分别表示每一个物品的 价格 和 美丽值 。
 * 同时给你一个下标从 0 开始的整数数组 queries 。对于每个查询 queries[j] ，你想求出价格小于等于 queries[j] 的物品中，最大的美丽值 是多少。
 * 如果不存在符合条件的物品，那么查询的结果为 0 。
 * 请你返回一个长度与 queries 相同的数组 answer，其中 answer[j]是第 j 个查询的答案。
 * 1 <= items.length, queries.length <= 10^5
 * items[i].length == 2
 * 1 <= pricei, beautyi, queries[j] <= 10^9
 */
public class Solution {

    public int[] maximumBeauty(int[][] items, int[] queries) {
        Arrays.sort(items, (a, b) -> Integer.compare(a[0], b[0]));
        int n = items.length;
        for (int i = 1; i < n; i++) {
            items[i][1] = Math.max(items[i][1], items[i - 1][1]);
        }
        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            res[i] = binarySearch(items, queries[i]);
        }
        return res;
    }

    private int binarySearch(int[][] items, int query) {
        int n = items.length;
        int left = 0, right = items.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (items[mid][0] > query) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (left == 0) {
            return 0;
        } else {
            return items[left - 1][1];
        }
    }

}
