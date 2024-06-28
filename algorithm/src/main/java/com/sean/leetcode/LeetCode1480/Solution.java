package com.sean.leetcode.LeetCode1480;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-08 21:24
 * @Description: https://leetcode.cn/problems/running-sum-of-1d-array/?plan=leetcode_75&plan_progress=zq8h8ym
 * 1480. 一维数组的动态和
 * 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
 * 请返回 nums 的动态和。
 */
public class Solution {

    public int[] runningSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int n = nums.length;
        int[] res = new int[n];
        res[0] = nums[0];
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] + nums[i];
        }
        return res;
    }

}
