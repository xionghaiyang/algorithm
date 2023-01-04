package com.sean.leetcode.LeetCode1567;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-14 21:56
 * @Description: https://leetcode.cn/problems/maximum-length-of-subarray-with-positive-product/?envType=study-plan&id=dong-tai-gui-hua-ru-men&plan=dynamic-programming&plan_progress=yn1qp15
 * 1567. 乘积为正数的最长子数组长度
 * 给你一个整数数组 nums ，请你求出乘积为正数的最长子数组的长度。
 * 一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。
 * 请你返回乘积为正数的最长子数组长度。
 */
public class Solution {

    public int getMaxLen(int[] nums) {
        int n = nums.length;
        int[] positive = new int[n];
        int[] negative = new int[n];
        if (nums[0] > 0) {
            positive[0] = 1;
        } else if (nums[0] < 0) {
            negative[0] = 1;
        }
        int res = positive[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > 0) {
                positive[i] = positive[i - 1] + 1;
                negative[i] = negative[i - 1] > 0 ? negative[i - 1] + 1 : 0;
            } else if (nums[i] < 0) {
                positive[i] = negative[i - 1] > 0 ? negative[i - 1] + 1 : 0;
                negative[i] = positive[i - 1] + 1;
            } else {
                positive[i] = 0;
                negative[i] = 0;
            }
            res = Math.max(res, positive[i]);
        }
        return res;
    }

}
