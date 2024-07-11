package com.sean.leetcode.LeetCode2972;

/**
 * @Author xionghaiyang
 * @Date 2024-07-11 08:02
 * @Description https://leetcode.cn/problems/count-the-number-of-incremovable-subarrays-ii/
 * 2972. 统计移除递增子数组的数目 II
 * 给你一个下标从 0 开始的 正 整数数组 nums 。
 * 如果 nums 的一个子数组满足：移除这个子数组后剩余元素 严格递增 ，那么我们称这个子数组为 移除递增 子数组。
 * 比方说，[5, 3, 4, 6, 7] 中的 [3, 4] 是一个移除递增子数组，因为移除该子数组后，[5, 3, 4, 6, 7] 变为 [5, 6, 7] ，是严格递增的。
 * 请你返回 nums 中 移除递增 子数组的总数目。
 * 注意 ，剩余元素为空的数组也视为是递增的。
 * 子数组 指的是一个数组中一段连续的元素序列。
 */
public class Solution {

    public long incremovableSubarrayCount(int[] nums) {
        long res = 0;
        int n = nums.length;
        int left = 0;
        while (left < n - 1) {
            if (nums[left] >= nums[left + 1]) {
                break;
            }
            left++;
        }
        if (left == n - 1) {
            return (long) n * (n + 1) / 2;
        }
        res += left + 2;
        for (int right = n - 1; right > 0; right--) {
            if (right < n - 1 && nums[right] >= nums[right + 1]) {
                break;
            }
            while (left >= 0 && nums[left] >= nums[right]) {
                left--;
            }
            res += left + 2;
        }
        return res;
    }

}
