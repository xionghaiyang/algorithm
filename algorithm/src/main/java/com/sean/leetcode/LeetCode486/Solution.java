package com.sean.leetcode.LeetCode486;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-04-04 20:36
 * @Description https://leetcode.cn/problems/predict-the-winner
 * 486. 预测赢家
 * 给你一个整数数组 nums 。
 * 玩家 1 和玩家 2 基于这个数组设计了一个游戏。
 * 玩家 1 和玩家 2 轮流进行自己的回合，玩家 1 先手。
 * 开始时，两个玩家的初始分值都是 0 。
 * 每一回合，玩家从数组的任意一端取一个数字（即，nums[0] 或 nums[nums.length - 1]），取到的数字将会从数组中移除（数组长度减 1 ）。
 * 玩家选中的数字将会加到他的得分上。
 * 当数组中没有剩余数字可取时，游戏结束。
 * 如果玩家 1 能成为赢家，返回 true 。
 * 如果两个玩家得分相等，同样认为玩家 1 是游戏的赢家，也返回 true 。
 * 你可以假设每个玩家的玩法都会使他的分数最大化。
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 10^7
 */
public class Solution {

    public boolean predictTheWinner(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int n = nums.length;
        int[][][] memo = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        int res = process1(nums, memo, 0, nums.length - 1);
        return res >= sum - res;
    }

    //玩家1先手能够获取的最大分数
    private int process1(int[] nums, int[][][] memo, int left, int right) {
        if (memo[left][right][0] != -1) {
            return memo[left][right][0];
        }
        if (left == right) {
            return memo[left][right][0] = nums[left];
        }
        return memo[left][right][0] = Math.max(nums[left] + process2(nums, memo, left + 1, right), nums[right] + process2(nums, memo, left, right - 1));
    }

    //玩家1后手能够获取的最大分数
    private int process2(int[] nums, int[][][] memo, int left, int right) {
        if (memo[left][right][1] != -1) {
            return memo[left][right][1];
        }
        if (left == right) {
            return memo[left][right][1] = 0;
        }
        return memo[left][right][1] = Math.min(process1(nums, memo, left + 1, right), process1(nums, memo, left, right - 1));
    }

}
