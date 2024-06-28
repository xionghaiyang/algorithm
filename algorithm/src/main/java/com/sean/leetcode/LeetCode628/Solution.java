package com.sean.leetcode.LeetCode628;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-24 19:34
 * @Description: https://leetcode.cn/problems/maximum-product-of-three-numbers/description/
 * 628. 三个数的最大乘积
 * 给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 */
public class Solution {

    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 3] * nums[n - 2] * nums[n - 1]);
    }

}
