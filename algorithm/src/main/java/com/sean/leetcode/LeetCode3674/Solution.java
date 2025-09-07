package com.sean.leetcode.LeetCode3674;

/**
 * @Author xionghaiyang
 * @Date 2025-09-07 18:27
 * @Description https://leetcode.cn/problems/minimum-operations-to-equalize-array
 * 3674. 数组元素相等的最小操作次数
 * 给你一个长度为 n 的整数数组 nums。
 * 在一次操作中，可以选择任意子数组 nums[l...r] （0 <= l <= r < n），并将该子数组中的每个元素 替换 为所有元素的 按位与（bitwise AND）结果。
 * 返回使数组 nums 中所有元素相等所需的最小操作次数。
 * 子数组 是数组中连续的、非空的元素序列。
 * 1 <= n == nums.length <= 100
 * 1 <= nums[i] <= 10^5
 */
public class Solution {

    public int minOperations(int[] nums) {
        int n = nums.length;
        int x = nums[0];
        for (int i = 1; i < n; i++) {
            if (x != nums[i]) {
                return 1;
            }
        }
        return 0;
    }

}
