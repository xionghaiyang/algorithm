package com.sean.leetcode.LeetCode3512;

/**
 * @Author xionghaiyang
 * @Date 2025-11-29 10:06
 * @Description https://leetcode.cn/problems/minimum-operations-to-make-array-sum-divisible-by-k
 * 3512. 使数组和能被 K 整除的最少操作次数
 * 给你一个整数数组 nums 和一个整数 k。
 * 你可以执行以下操作任意次：
 * 选择一个下标 i，并将 nums[i] 替换为 nums[i] - 1。
 * 返回使数组元素之和能被 k 整除所需的最小操作次数。
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * 1 <= k <= 100
 */
public class Solution {

    public int minOperations(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum % k;
    }

}
