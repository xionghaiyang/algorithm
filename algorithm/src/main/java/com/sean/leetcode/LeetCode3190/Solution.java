package com.sean.leetcode.LeetCode3190;

/**
 * @Author xionghaiyang
 * @Date 2025-11-22 12:11
 * @Description https://leetcode.cn/problems/find-minimum-operations-to-make-all-elements-divisible-by-three
 * 3190. 使所有元素都可以被 3 整除的最少操作数
 * 给你一个整数数组 nums 。
 * 一次操作中，你可以将 nums 中的 任意 一个元素增加或者减少 1 。
 * 请你返回将 nums 中所有元素都可以被 3 整除的 最少 操作次数。
 * 1 <= nums.length <= 50
 * 1 <= nums[i] <= 50
 */
public class Solution {

    public int minimumOperations(int[] nums) {
        int res = 0;
        for (int num : nums) {
            if (num % 3 != 0) {
                res++;
            }
        }
        return res;
    }

}
