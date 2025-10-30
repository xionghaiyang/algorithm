package com.sean.leetcode.LeetCode2461;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-10-30 17:22
 * @Description https://leetcode.cn/problems/maximum-sum-of-distinct-subarrays-with-length-k
 * 2461. 长度为 K 子数组中的最大和
 * 给你一个整数数组 nums 和一个整数 k 。
 * 请你从 nums 中满足下述条件的全部子数组中找出最大子数组和：
 * 子数组的长度是 k，且
 * 子数组中的所有元素 各不相同 。
 * 返回满足题面要求的最大子数组和。
 * 如果不存在子数组满足这些条件，返回 0 。
 * 子数组 是数组中一段连续非空的元素序列。
 * 1 <= k <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 */
public class Solution {

    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long res = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            int left = i - k + 1;
            if (left < 0) {
                continue;
            }
            if (map.size() == k) {
                res = Math.max(res, sum);
            }
            int out = nums[left];
            sum -= out;
            int cnt = map.get(out);
            if (cnt > 1) {
                map.put(out, cnt - 1);
            } else {
                map.remove(out);
            }
        }
        return res;
    }

}
