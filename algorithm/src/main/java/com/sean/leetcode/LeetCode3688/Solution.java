package com.sean.leetcode.LeetCode3688;

/**
 * @Author xionghaiyang
 * @Date 2025-09-22 19:52
 * @Description https://leetcode.cn/problems/bitwise-or-of-even-numbers-in-an-array
 * 3688. 偶数的按位或运算
 * 给你一个整数数组 nums。
 * 返回数组中所有 偶数 的按位 或 运算结果。
 * 如果 nums 中没有偶数，返回 0。
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 */
public class Solution {

    public int evenNumberBitwiseORs(int[] nums) {
        int res = 0;
        for (int num : nums) {
            if ((num & 1) == 0) {
                res |= num;
            }
        }
        return res;
    }

}
