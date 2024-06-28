package com.sean.leetcode.LeetCode1330;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-12 08:17
 * @Description: https://leetcode.cn/problems/reverse-subarray-to-maximize-array-value/
 * 1330. 翻转子数组得到最大的数组值
 * 给你一个整数数组 nums 。
 * 「数组值」定义为所有满足 0 <= i < nums.length-1 的 |nums[i]-nums[i+1]| 的和。
 * 你可以选择给定数组的任意子数组，并将该子数组翻转。但你只能执行这个操作 一次 。
 * 请你找到可行的最大 数组值 。
 */
public class Solution {

    public int maxValueAfterReverse1(int[] nums) {
        int n = nums.length;
        int value = 0;
        for (int i = 0; i < n - 1; i++) {
            value += Math.abs(nums[i] - nums[i + 1]);
        }
        int max = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                max = Math.max(max, process(nums, i, j));
            }
        }
        return value + max;
    }

    private int process(int[] nums, int i, int j) {
        int n = nums.length;
        if (i == 0) {
            if (j == n - 1) {
                return 0;
            } else {
                return Math.abs(nums[j + 1] - nums[i]) - Math.abs(nums[j] - nums[j + 1]);
            }
        } else {
            if (j == n - 1) {
                return Math.abs(nums[j] - nums[i - 1]) - Math.abs(nums[i] - nums[i - 1]);
            } else {
                return (Math.abs(nums[j] - nums[i - 1]) - Math.abs(nums[i] - nums[i - 1])) + (Math.abs(nums[j + 1] - nums[i]) - Math.abs(nums[j] - nums[j + 1]));
            }
        }
    }

    public int maxValueAfterReverse(int[] nums) {
        int n = nums.length;
        int value = 0;
        for (int i = 0; i < n - 1; i++) {
            value += Math.abs(nums[i] - nums[i + 1]);
        }
        int mx1 = 0;
        for (int i = 1; i < n - 1; i++) {
            mx1 = Math.max(mx1, Math.abs(nums[0] - nums[i + 1]) - Math.abs(nums[i] - nums[i + 1]));
            mx1 = Math.max(mx1, Math.abs(nums[n - 1] - nums[i - 1]) - Math.abs(nums[i] - nums[i - 1]));
        }
        int mx2 = Integer.MIN_VALUE;
        int mn2 = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            mx2 = Math.max(mx2, Math.min(nums[i], nums[i + 1]));
            mn2 = Math.min(mn2, Math.max(nums[i], nums[i + 1]));
        }
        return value + Math.max(mx1, 2 * (mx2 - mn2));
    }

}
