package com.sean.leetcode.LeetCode2778;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-11 06:09
 * @Description: https://leetcode.cn/problems/sum-of-squares-of-special-elements
 * 2778. 特殊元素平方和
 * 给你一个下标从 1 开始、长度为 n 的整数数组 nums 。
 * 对 nums 中的元素 nums[i] 而言，如果 n 能够被 i 整除，即 n % i == 0 ，则认为 nums[i] 是一个 特殊元素 。
 * 返回 nums 中所有 特殊元素 的 平方和 。
 * 1 <= nums.length == n <= 50
 * 1 <= nums[i] <= 50
 */
public class Solution {

    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (n % (i + 1) == 0) {
                res += nums[i] * nums[i];
            }
        }
        return res;
    }

}
