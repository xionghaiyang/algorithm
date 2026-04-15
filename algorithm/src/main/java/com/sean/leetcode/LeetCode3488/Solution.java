package com.sean.leetcode.LeetCode3488;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2026-04-16 05:52
 * @Description https://leetcode.cn/problems/closest-equal-element-queries
 * 3488. 距离最小相等元素查询
 * 给你一个 环形 数组 nums 和一个数组 queries 。
 * 对于每个查询 i ，你需要找到以下内容：
 * 数组 nums 中下标 queries[i] 处的元素与 任意 其他下标 j（满足 nums[j] == nums[queries[i]]）之间的 最小 距离。
 * 如果不存在这样的下标 j，则该查询的结果为 -1 。
 * 返回一个数组 answer，其大小与 queries 相同，其中 answer[i] 表示查询i的结果。
 * 1 <= queries.length <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 * 0 <= queries[i] < nums.length
 */
public class Solution {

    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], e -> new ArrayList<>()).add(i);
        }
        for (List<Integer> list : map.values()) {
            int i = list.get(0);
            list.add(0, list.get(list.size() - 1) - n);
            list.add(i + n);
        }
        List<Integer> res = new ArrayList<>(queries.length);
        for (int query : queries) {
            List<Integer> list = map.get(nums[query]);
            if (list.size() == 3) {
                res.add(-1);
            } else {
                int i = binarySearch(list, query);
                res.add(Math.min(query - list.get(i - 1), list.get(i + 1) - query));
            }
        }
        return res;
    }

    private int binarySearch(List<Integer> list, int query) {
        int res = -1;
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (list.get(mid) == query) {
                res = mid;
                break;
            } else if (list.get(mid) > query) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

}
