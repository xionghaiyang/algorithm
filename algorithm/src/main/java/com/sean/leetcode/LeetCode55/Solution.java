package com.sean.leetcode.LeetCode55;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-27 19:55
 * @Description: https://leetcode.cn/problems/jump-game/?plan=zhijigangwei&plan_progress=stbyva7
 * 55. 跳跃游戏
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 */
public class Solution {

    public boolean canJump(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (i <= max) {
                max = Math.max(max, i + nums[i]);
            }
            if (max >= n - 1) {
                return true;
            }
        }
        return false;
    }

}
