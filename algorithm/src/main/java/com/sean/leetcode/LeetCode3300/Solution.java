package com.sean.leetcode.LeetCode3300;

/**
 * @Author: xionghaiyang
 * @Date: 2026-05-29 12:04
 * @Description: https://leetcode.cn/problems/minimum-element-after-replacement-with-digit-sum
 * 3300. 替换为数位和以后的最小元素
 * 给你一个整数数组 nums 。
 * 请你将 nums 中每一个元素都替换为它的各个数位之 和 。
 * 请你返回替换所有元素以后 nums 中的 最小 元素。
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 10^4
 */
public class Solution {

    public int minElement(int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int num : nums) {
            res = Math.min(res, getSum(num));
        }
        return res;
    }

    private int getSum(int num) {
        int res = 0;
        while (num > 0) {
            res += num % 10;
            num /= 10;
        }
        return res;
    }

}
