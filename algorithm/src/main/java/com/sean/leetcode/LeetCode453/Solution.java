package com.sean.leetcode.LeetCode453;

/**
 * @Author xionghaiyang
 * @Date 2026-04-04 19:18
 * @Description https://leetcode.cn/problems/minimum-moves-to-equal-array-elements
 * 453. 最小操作次数使数组元素相等
 * 给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。
 * 返回让数组所有元素相等的最小操作次数。
 * n == nums.length
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * 答案保证符合 32-bit 整数
 */
public class Solution {

    public int minMoves(int[] nums) {
        long sum = 0;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            sum += num;
            min = Math.min(min, num);
        }
        return (int) (sum - (long) min * nums.length);
    }

}
