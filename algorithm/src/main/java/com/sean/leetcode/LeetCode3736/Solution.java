package com.sean.leetcode.LeetCode3736;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-03 06:16
 * @Description: https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-iii
 * 3736. 最小操作次数使数组元素相等 III
 * 给你一个整数数组 nums。
 * 在一步操作中，你可以将任意单个元素 nums[i] 的值 增加 1。
 * 返回使数组中的所有元素都 相等 所需的 最小总操作次数 。
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 */
public class Solution {

    public int minMoves(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int res = 0;
        for (int num : nums) {
            res += max - num;
        }
        return res;
    }

}
