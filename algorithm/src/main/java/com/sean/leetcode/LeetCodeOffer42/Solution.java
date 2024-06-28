package com.sean.leetcode.LeetCodeOffer42;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-28 20:18
 * @Description: https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/?plan=lcof&plan_progress=zq3t7ii
 * 剑指 Offer 42. 连续子数组的最大和
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * 要求时间复杂度为O(n)。
 */
public class Solution {

    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (sum < 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            res = Math.max(res, sum);
        }
        return res;
    }

}
