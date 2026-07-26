package com.sean.leetcode.LeetCode1464;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-26 08:32
 * @Description: https://leetcode.cn/problems/maximum-product-of-two-elements-in-an-array
 * 1464. 数组中两元素的最大乘积
 * 给你一个整数数组 nums，请你选择数组的两个不同下标 i 和 j，使 (nums[i]-1)*(nums[j]-1) 取得最大值。
 * 请你计算并返回该式的最大值。
 * 2 <= nums.length <= 500
 * 1 <= nums[i] <= 10^3
 */
public class Solution {

    public int maxProduct(int[] nums) {
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > first) {
                second = first;
                first = num;
            } else if (num > second) {
                second = num;
            }
        }
        return (first - 1) * (second - 1);
    }

}
