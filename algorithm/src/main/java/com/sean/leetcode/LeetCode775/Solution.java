package com.sean.leetcode.LeetCode775;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-16 08:21
 * @Description: https://leetcode.cn/problems/global-and-local-inversions/
 * 775. 全局倒置与局部倒置
 * 给你一个长度为 n 的整数数组 nums ，表示由范围 [0, n - 1] 内所有整数组成的一个排列。
 * 全局倒置 的数目等于满足下述条件不同下标对 (i, j) 的数目：
 * 0 <= i < j < n
 * nums[i] > nums[j]
 * 局部倒置 的数目等于满足下述条件的下标 i 的数目：
 * 0 <= i < n - 1
 * nums[i] > nums[i + 1]
 * 当数组 nums 中 全局倒置 的数量等于 局部倒置 的数量时，返回 true ；否则，返回 false 。
 */
public class Solution {

    public boolean isIdealPermutation(int[] nums) {
        int n = nums.length;
        int min = nums[n - 1];
        for (int i = n - 3; i >= 0; i--) {
            if (nums[i] > min) {
                return false;
            }
            min = Math.min(min, nums[i + 1]);
        }
        return true;
    }

}
