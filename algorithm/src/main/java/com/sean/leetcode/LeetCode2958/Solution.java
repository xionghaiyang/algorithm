package com.sean.leetcode.LeetCode2958;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-12 05:47
 * @Description: https://leetcode.cn/problems/length-of-longest-subarray-with-at-most-k-frequency
 * 2958. 最多 K 个重复元素的最长子数组
 * 给你一个整数数组 nums 和一个整数 k 。
 * 一个元素 x 在数组中的 频率 指的是它在数组中的出现次数。
 * 如果一个数组中所有元素的频率都 小于等于 k ，那么我们称这个数组是 好 数组。
 * 请你返回 nums 中 最长好 子数组的长度。
 * 子数组 指的是一个数组中一段连续非空的元素序列。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= nums.length
 */
public class Solution {

    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int left = 0, right = -1; left < n; left++) {
            while (right + 1 < n && map.getOrDefault(nums[right + 1], 0) < k) {
                map.merge(nums[++right], 1, Integer::sum);
            }
            res = Math.max(res, right - left + 1);
            map.merge(nums[left], -1, Integer::sum);
        }
        return res;
    }

}
