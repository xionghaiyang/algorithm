package com.sean.leetcode.LeetCode2770;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-05-10 08:41
 * @Description: https://leetcode.cn/problems/maximum-number-of-jumps-to-reach-the-last-index
 * 2770. 达到末尾下标所需的最大跳跃次数
 * 给你一个下标从 0 开始、由 n 个整数组成的数组 nums 和一个整数 target 。
 * 你的初始位置在下标 0 。
 * 在一步操作中，你可以从下标 i 跳跃到任意满足下述条件的下标 j ：
 * 0 <= i < j < n
 * -target <= nums[j] - nums[i] <= target
 * 返回到达下标 n - 1 处所需的 最大跳跃次数 。
 * 如果无法到达下标 n - 1 ，返回 -1 。
 * 2 <= nums.length == n <= 1000
 * -10^9 <= nums[i] <= 10^9
 * 0 <= target <= 2 * 10^9
 */
public class Solution {

    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        int[] memo = new int[n];
        Arrays.fill(memo, Integer.MIN_VALUE);
        return process(nums, target, memo, n - 1);
    }

    private int process(int[] nums, int target, int[] memo, int j) {
        if (memo[j] != Integer.MIN_VALUE) {
            return memo[j];
        }
        if (j == 0) {
            return memo[j] = 0;
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < j; i++) {
            if (-target <= nums[j] - nums[i] && nums[j] - nums[i] <= target) {
                int res1 = process(nums, target, memo, i);
                if (res1 != -1) {
                    res = Math.max(res, res1 + 1);
                }
            }
        }
        return memo[j] = res != Integer.MIN_VALUE ? res : -1;
    }

}
