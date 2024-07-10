package com.sean.leetcode.LeetCode2970;

/**
 * @Author xionghaiyang
 * @Date 2024-07-10 07:31
 * @Description https://leetcode.cn/problems/count-the-number-of-incremovable-subarrays-i/
 * 2970. 统计移除递增子数组的数目 I
 * 给你一个下标从 0 开始的 正 整数数组 nums 。
 * 如果 nums 的一个子数组满足：移除这个子数组后剩余元素 严格递增 ，那么我们称这个子数组为 移除递增 子数组。
 * 比方说，[5, 3, 4, 6, 7] 中的 [3, 4] 是一个移除递增子数组，因为移除该子数组后，[5, 3, 4, 6, 7] 变为 [5, 6, 7] ，是严格递增的。
 * 请你返回 nums 中 移除递增 子数组的总数目。
 * 注意 ，剩余元素为空的数组也视为是递增的。
 * 子数组 指的是一个数组中一段连续的元素序列。
 */
public class Solution {

    public int incremovableSubarrayCount(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int left = 0; left < n; left++) {
            for (int right = left; right < n; right++) {
                if (isIncreasing(nums, left, right)) {
                    res++;
                }
            }
        }
        return res;
    }

    private boolean isIncreasing(int[] nums, int left, int right) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (i >= left && i <= right + 1) {
                continue;
            }
            if (nums[i] <= nums[i - 1]) {
                return false;
            }
        }
        if (left - 1 >= 0 && right + 1 < n && nums[right + 1] <= nums[left - 1]) {
            return false;
        }
        return true;
    }

    public int incremovableSubarrayCount1(int[] nums) {
        int n = nums.length;
        int res = 0;
        int left = 1;
        while (left < n && nums[left - 1] < nums[left]) {
            left++;
        }
        res += left + (left < n ? 1 : 0);
        for (int right = n - 2; right >= 0; right--) {
            while (left > 0 && nums[left - 1] >= nums[right + 1]) {
                left--;
            }
            res += left + (left <= right ? 1 : 0);
            if (nums[right] >= nums[right + 1]) {
                break;
            }
        }
        return res;
    }

}
