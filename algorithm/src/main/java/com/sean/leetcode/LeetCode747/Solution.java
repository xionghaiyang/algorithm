package com.sean.leetcode.LeetCode747;

/**
 * @Author xionghaiyang
 * @Date 2025-09-29 12:27
 * @Description https://leetcode.cn/problems/largest-number-at-least-twice-of-others
 * 747. 至少是其他数字两倍的最大数
 * 给你一个整数数组 nums ，其中总是存在 唯一的 一个最大整数 。
 * 请你找出数组中的最大元素并检查它是否 至少是数组中每个其他数字的两倍 。
 * 如果是，则返回 最大元素的下标 ，否则返回 -1 。
 * 2 <= nums.length <= 50
 * 0 <= nums[i] <= 100
 * nums 中的最大元素是唯一的
 */
public class Solution {

    public int dominantIndex(int[] nums) {
        int n = nums.length;
        int max1 = -1, max2 = -1;
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] > max1) {
                max2 = max1;
                max1 = nums[i];
                index = i;
            } else if (nums[i] > max2) {
                max2 = nums[i];
            }
        }
        return max1 >= max2 * 2 ? index : -1;
    }

}
