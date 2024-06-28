package com.sean.leetcode.LeetCode2765;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-23 14:42
 * @Description: https://leetcode.cn/problems/longest-alternating-subarray/
 * 2765. 最长交替子数组
 * 给你一个下标从 0 开始的整数数组 nums 。
 * 如果 nums 中长度为 m 的子数组 s 满足以下条件，我们称它是一个 交替子数组 ：
 * m 大于 1 。
 * s1 = s0 + 1 。
 * 下标从 0 开始的子数组 s 与数组 [s0, s1, s0, s1,...,s(m-1) % 2] 一样。
 * 也就是说，s1 - s0 = 1 ，s2 - s1 = -1 ，s3 - s2 = 1 ，s4 - s3 = -1 ，以此类推，直到 s[m - 1] - s[m - 2] = (-1)m 。
 * 请你返回 nums 中所有 交替 子数组中，最长的长度，如果不存在交替子数组，请你返回 -1 。
 * 子数组是一个数组中一段连续 非空 的元素序列。
 */
public class Solution {

    public int alternatingSubarray(int[] nums) {
        int res = -1;
        int length = -1;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (length > 0 && nums[i] == nums[i - 2]) {
                length++;
            } else if (nums[i] - nums[i - 1] == 1) {
                length = 2;
            } else {
                length = -1;
            }
            res = Math.max(res, length);
        }
        return res;
    }

}
