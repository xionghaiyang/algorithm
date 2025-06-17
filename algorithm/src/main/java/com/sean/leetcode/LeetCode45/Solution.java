package com.sean.leetcode.LeetCode45;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-14 15:00
 * @Description: https://leetcode.cn/problems/jump-game-ii
 * 45. 跳跃游戏 II
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 1000
 * 题目保证可以到达 nums[n-1]
 */
public class Solution {

    public int jump(int[] nums) {
        int n = nums.length;
        int maxPosition = 0, end = 0;
        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                res++;
            }
        }
        return res;
    }

}
