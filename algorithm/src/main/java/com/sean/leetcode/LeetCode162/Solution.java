package com.sean.leetcode.LeetCode162;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-18 10:54
 * @Description: https://leetcode.cn/problems/find-peak-element
 * 162. 寻找峰值
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 * 1 <= nums.length <= 1000
 * -2^31 <= nums[i] <= 2^31 - 1
 * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 */
public class Solution {

    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1, res = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (compare(nums, mid - 1, mid) < 0 && compare(nums, mid, mid + 1) > 0) {
                res = mid;
                break;
            }
            if (compare(nums, mid, mid + 1) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    private int compare(int[] nums, int i, int j) {
        if (i == -1) {
            return -1;
        } else if (j == nums.length) {
            return 1;
        }
        return nums[i] > nums[j] ? 1 : -1;
    }

}
