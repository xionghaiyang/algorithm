package com.sean.leetcode.LeetCode896;

/**
 * @Author xionghaiyang
 * @Date 2026-04-10 12:49
 * @Description https://leetcode.cn/problems/monotonic-array
 * 896. 单调数列
 * 如果数组是单调递增或单调递减的，那么它是 单调 的。
 * 如果对于所有 i <= j，nums[i] <= nums[j]，那么数组 nums 是单调递增的。
 * 如果对于所有 i <= j，nums[i] >= nums[j]，那么数组 nums 是单调递减的。
 * 当给定的数组 nums 是单调数组时返回 true，否则返回 false。
 * 1 <= nums.length <= 10^5
 * -10^5 <= nums[i] <= 10^5
 */
public class Solution {

    public boolean isMonotonic(int[] nums) {
        if (nums.length <= 2) {
            return true;
        }
        int n = nums.length;
        int i = 1;
        while (nums[i - 1] == nums[i] && i + 1 < n) {
            i++;
        }
        boolean flag = nums[i - 1] < nums[i];
        for (i++; i < n; i++) {
            if (nums[i - 1] == nums[i]) {
                continue;
            } else if ((nums[i - 1] < nums[i]) ^ flag) {
                return false;
            }
        }
        return true;
    }

}
