package com.sean.leetcode.LeetCode1413;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-09 11:33
 * @Description: https://leetcode.cn/problems/minimum-value-to-get-positive-step-by-step-sum/
 * 1413. 逐步求和得到正数的最小值
 * 给你一个整数数组 nums 。你可以选定任意的 正数 startValue 作为初始值。
 * 你需要从左到右遍历 nums 数组，并将 startValue 依次累加上 nums 数组中的值。
 * 请你在确保累加和始终大于等于 1 的前提下，选出一个最小的 正数 作为 startValue 。
 */
public class Solution {

    public int minStartValue(int[] nums) {
        int n = nums.length;
        int preSum = 0, startValue = Integer.MAX_VALUE;
        for (int num : nums) {
            preSum += num;
            startValue = Math.min(startValue, preSum);
        }
        return startValue < 0 ? -startValue + 1 : 1;
    }

}
