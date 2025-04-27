package com.sean.leetcode.LeetCode3392;

/**
 * @Author xionghaiyang
 * @Date 2025-04-27 08:24
 * @Description https://leetcode.cn/problems/count-subarrays-of-length-three-with-a-condition
 * 3392. 统计符合条件长度为 3 的子数组数目
 * 给你一个整数数组 nums ，请你返回长度为 3 的 子数组，满足第一个数和第三个数的和恰好为第二个数的一半。
 * 子数组 指的是一个数组中连续 非空 的元素序列。
 * 3 <= nums.length <= 100
 * -100 <= nums[i] <= 100
 */
public class Solution {

    public int countSubarrays(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = 1; i < n - 1; i++) {
            if ((nums[i - 1] + nums[i + 1]) * 2 == nums[i]) {
                res++;
            }
        }
        return res;
    }

}
