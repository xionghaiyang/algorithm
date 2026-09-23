package com.sean.leetcode.LeetCode3550;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-24 06:29
 * @Description: https://leetcode.cn/problems/smallest-index-with-digit-sum-equal-to-index
 * 3550. 数位和等于下标的最小下标
 * 给你一个整数数组 nums 。
 * 返回满足 nums[i] 的数位和（每一位数字相加求和）等于 i 的 最小 下标 i 。
 * 如果不存在满足要求的下标，返回 -1 。
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */
public class Solution {

    public int smallestIndex(int[] nums) {
        int n = Math.min(nums.length, 28);
        int res = -1;
        for (int i = 0; i < n; i++) {
            if (check(nums[i], i)) {
                res = i;
                break;
            }
        }
        return res;
    }

    private boolean check(int num, int sum) {
        while (num > 0 && sum > 0) {
            sum -= num % 10;
            num /= 10;
        }
        return num == 0 && sum == 0;
    }

}
