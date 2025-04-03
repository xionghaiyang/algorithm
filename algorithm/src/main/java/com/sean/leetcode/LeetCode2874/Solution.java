package com.sean.leetcode.LeetCode2874;

/**
 * @Author xionghaiyang
 * @Date 2025-04-03 10:27
 * @Description https://leetcode.cn/problems/maximum-value-of-an-ordered-triplet-ii
 * 2874. 有序三元组中的最大值 II
 * 给你一个下标从 0 开始的整数数组 nums 。
 * 请你从所有满足 i < j < k 的下标三元组 (i, j, k) 中，找出并返回下标三元组的最大值。
 * 下标三元组 (i, j, k) 的值等于 (nums[i] - nums[j]) * nums[k] 。
 * 3 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 */
public class Solution {

    public long maximumTripletValue(int[] nums) {
        long res = 0;
        int maxDiff = 0;
        int maxPre = 0;
        for (int num : nums) {
            res = Math.max(res, (long) maxDiff * num);
            maxDiff = Math.max(maxDiff, maxPre - num);
            maxPre = Math.max(maxPre, num);
        }
        return res;
    }

}
