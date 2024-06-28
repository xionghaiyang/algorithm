package com.sean.leetcode.LeetCode1800;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-07 21:56
 * @Description: https://leetcode.cn/problems/maximum-ascending-subarray-sum/
 * 1800. 最大升序子数组和
 * 给你一个正整数组成的数组 nums ，返回 nums 中一个 升序 子数组的最大可能元素和。
 * 子数组是数组中的一个连续数字序列。
 * 已知子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，
 * 若对所有 i（l <= i < r），numsi < numsi+1 都成立，则称这一子数组为 升序 子数组。
 * 注意，大小为 1 的子数组也视作 升序 子数组。
 */
public class Solution {

    public int maxAscendingSum(int[] nums) {
        int res = 0;
        int index = 0;
        int n = nums.length;
        while (index < n) {
            int sum = nums[index++];
            while (index < n && nums[index] > nums[index - 1]) {
                sum += nums[index++];
            }
            res = Math.max(res, sum);
        }
        return res;
    }

}
