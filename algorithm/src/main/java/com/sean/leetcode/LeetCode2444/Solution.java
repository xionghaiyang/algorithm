package com.sean.leetcode.LeetCode2444;

/**
 * @Author xionghaiyang
 * @Date 2025-04-26 06:16
 * @Description https://leetcode.cn/problems/count-subarrays-with-fixed-bounds
 * 2444. 统计定界子数组的数目
 * 给你一个整数数组 nums 和两个整数 minK 以及 maxK 。
 * nums 的定界子数组是满足下述条件的一个子数组：
 * 子数组中的 最小值 等于 minK 。
 * 子数组中的 最大值 等于 maxK 。
 * 返回定界子数组的数目。
 * 子数组是数组中的一个连续部分。
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i], minK, maxK <= 10^6
 */
public class Solution {

    public long countSubarrays(int[] nums, int minK, int maxK) {
        long res = 0;
        int n = nums.length;
        int left = -1, last_min = -1, last_max = -1;
        for (int right = 0; right < n; right++) {
            if (nums[right] < minK || nums[right] > maxK) {
                left = right;
                last_min = -1;
                last_max = -1;
            }
            if (nums[right] == minK) {
                last_min = right;
            }
            if (nums[right] == maxK) {
                last_max = right;
            }
            if (last_min != -1 && last_max != -1) {
                res += Math.min(last_min, last_max) - left;
            }
        }
        return res;
    }

}
