package com.sean.leetcode.LeetCode2302;

/**
 * @Author xionghaiyang
 * @Date 2025-04-28 08:23
 * @Description https://leetcode.cn/problems/count-subarrays-with-score-less-than-k
 * 2302. 统计得分小于 K 的子数组数目
 * 一个数组的 分数 定义为数组之和 乘以 数组的长度。
 * 比方说，[1, 2, 3, 4, 5] 的分数为 (1 + 2 + 3 + 4 + 5) * 5 = 75 。
 * 给你一个正整数数组 nums 和一个整数 k ，请你返回 nums 中分数 严格小于 k 的 非空整数子数组数目。
 * 子数组 是数组中的一个连续元素序列。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= 10^15
 */
public class Solution {

    public long countSubarrays(int[] nums, long k) {
        int n = nums.length;
        long res = 0, sum = 0;
        for (int left = 0, right = 0; right < n; right++) {
            sum += nums[right];
            while (sum * (right - left + 1) >= k) {
                sum -= nums[left++];
            }
            res += right - left + 1;
        }
        return res;
    }

}
