package com.sean.leetcode.LeetCode2841;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-10-30 12:28
 * @Description https://leetcode.cn/problems/maximum-sum-of-almost-unique-subarray
 * 2841. 几乎唯一子数组的最大和
 * 给你一个整数数组 nums 和两个正整数 m 和 k 。
 * 请你返回 nums 中长度为 k 的 几乎唯一 子数组的 最大和 ，如果不存在几乎唯一子数组，请你返回 0 。
 * 如果 nums 的一个子数组有至少 m 个互不相同的元素，我们称它是 几乎唯一 子数组。
 * 子数组指的是一个数组中一段连续 非空 的元素序列。
 * 1 <= nums.length <= 2 * 10^4
 * 1 <= m <= k <= nums.length
 * 1 <= nums[i] <= 10^9
 */
public class Solution {

    public long maxSum(List<Integer> nums, int m, int k) {
        int size = nums.size();
        long res = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            sum += nums.get(i);
            map.put(nums.get(i), map.getOrDefault(nums.get(i), 0) + 1);
            int left = i - k + 1;
            if (left < 0) {
                continue;
            }
            if (map.size() >= m) {
                res = Math.max(res, sum);
            }
            int out = nums.get(left);
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
