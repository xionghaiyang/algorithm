package com.sean.leetcode.LeetCode2389;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-05-07 10:24
 * @Description https://leetcode.cn/problems/longest-subsequence-with-limited-sum/
 * 2389. 和有限的最长子序列
 * 给你一个长度为 n 的整数数组 nums ，和一个长度为 m 的整数数组 queries 。
 * 返回一个长度为 m 的数组 answer ，其中 answer[i] 是 nums 中 元素之和小于等于 queries[i] 的 子序列 的 最大 长度  。
 * 子序列 是由一个数组删除某些元素（也可以不删除）但不改变剩余元素顺序得到的一个数组。
 */
public class Solution {

    public int[] answerQueries(int[] nums, int[] queries) {
        int n = nums.length, m = queries.length;
        Arrays.sort(nums);
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            res[i] = binarySearch(preSum, queries[i]) - 1;
        }
        return res;
    }

    private int binarySearch(int[] preSum, int target) {
        int low = 1, high = preSum.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (preSum[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

}
