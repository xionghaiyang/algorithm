package com.sean.leetcode.LeetCode3660;

/**
 * @Author: xionghaiyang
 * @Date: 2026-05-07 08:54
 * @Description: https://leetcode.cn/problems/jump-game-ix
 * 3660. 跳跃游戏 IX
 * 给你一个整数数组 nums。
 * 从任意下标 i 出发，你可以根据以下规则跳跃到另一个下标 j：
 * 仅当 nums[j] < nums[i] 时，才允许跳跃到下标 j，其中 j > i。
 * 仅当 nums[j] > nums[i] 时，才允许跳跃到下标 j，其中 j < i。
 * 对于每个下标 i，找出从 i 出发且可以跳跃 任意 次，能够到达 nums 中的 最大值 是多少。
 * 返回一个数组 ans，其中 ans[i] 是从下标 i 出发可以到达的最大值。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 */
public class Solution {

    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] preMax = new int[n];
        preMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preMax[i] = Math.max(preMax[i - 1], nums[i]);
        }
        int[] res = new int[n];
        int sufMin = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            res[i] = preMax[i] <= sufMin ? preMax[i] : res[i + 1];
            sufMin = Math.min(sufMin, nums[i]);
        }
        return res;
    }

}
