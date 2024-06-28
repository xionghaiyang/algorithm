package com.sean.leetcode.LeetCode3026;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-05-09 19:15
 * @Description https://leetcode.cn/problems/maximum-good-subarray-sum/
 * 3026. 最大好子数组和
 * 给你一个长度为 n 的数组 nums 和一个 正 整数 k 。
 * 如果 nums 的一个 子数组 中，第一个元素和最后一个元素 差的绝对值恰好 为 k ，我们称这个子数组为 好 的。
 * 换句话说，如果子数组 nums[i..j] 满足 |nums[i] - nums[j]| == k ，那么它是一个好子数组。
 * 请你返回 nums 中 好 子数组的 最大 和，如果没有好子数组，返回 0 。
 */
public class Solution {

    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        long res = Long.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i] + k)) {
                int prevIndex = map.get(nums[i] + k);
                res = Math.max(res, preSum[i + 1] - preSum[prevIndex]);
            }
            if (map.containsKey(nums[i] - k)) {
                int prevIndex = map.get(nums[i] - k);
                res = Math.max(res, preSum[i + 1] - preSum[prevIndex]);
            }
            if (map.containsKey(nums[i])) {
                int preIndex = map.get(nums[i]);
                map.put(nums[i], preSum[preIndex + 1] <= preSum[i + 1] ? preIndex : i);
            } else {
                map.put(nums[i], i);
            }
        }
        return res == Long.MIN_VALUE ? 0 : res;
    }

}
