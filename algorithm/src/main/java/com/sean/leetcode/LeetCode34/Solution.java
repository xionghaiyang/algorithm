package com.sean.leetcode.LeetCode34;

/**
 * @Author xionghaiyang
 * @Date 2025-06-14 10:41
 * @Description https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。
 * 请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * nums 是一个非递减数组
 * -10^9 <= target <= 10^9
 */
public class Solution {

    public int[] searchRange(int[] nums, int target) {
        int start = getStart(nums, target);
        int end = getEnd(nums, target);
        return new int[]{start, end};
    }

    private int getStart(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int res = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    private int getEnd(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int res = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                res = mid;
                left = mid + 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

}
