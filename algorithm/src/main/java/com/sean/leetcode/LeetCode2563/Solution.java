package com.sean.leetcode.LeetCode2563;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-04-19 08:48
 * @Description https://leetcode.cn/problems/count-the-number-of-fair-pairs
 * 2563. 统计公平数对的数目
 * 给你一个下标从 0 开始、长度为 n 的整数数组 nums ，和两个整数 lower 和 upper ，返回 公平数对的数目 。
 * 如果 (i, j) 数对满足以下情况，则认为它是一个 公平数对 ：
 * 0 <= i < j < n，且
 * lower <= nums[i] + nums[j] <= upper
 * 1 <= nums.length <= 10^5
 * nums.length == n
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= lower <= upper <= 10^9
 */
public class Solution {

    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        long res = 0;
        for (int j = 0; j < nums.length; j++) {
            int right = binarySearch(nums, j, upper - nums[j] + 1);
            int left = binarySearch(nums, j, lower - nums[j]);
            res += right - left;
        }
        return res;
    }

    private int binarySearch(int[] nums, int right, int target) {
        int left = -1;
        while (left + 1 < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    public long countFairPairs1(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        long res = 0;
        int n = nums.length;
        for (int j = 0, left = n, right = n; j < n; j++) {
            while (right > 0 && nums[right - 1] + nums[j] > upper) {
                right--;
            }
            while (left > 0 && nums[left - 1] + nums[j] >= lower) {
                left--;
            }
            res += Math.min(right, j) - Math.min(left, j);
        }
        return res;
    }

}
