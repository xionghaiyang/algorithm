package com.sean.leetcode.LeetCode1848;

/**
 * @Author xionghaiyang
 * @Date 2026-04-13 06:35
 * @Description https://leetcode.cn/problems/minimum-distance-to-the-target-element
 * 1848. 到目标元素的最小距离
 * 给你一个整数数组 nums （下标 从 0 开始 计数）以及两个整数 target 和 start ，请你找出一个下标 i ，满足 nums[i] == target 且 abs(i - start) 最小化 。
 * 注意：abs(x) 表示 x 的绝对值。
 * 返回 abs(i - start) 。
 * 题目数据保证 target 存在于 nums 中。
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 10^4
 * 0 <= start < nums.length
 * target 存在于 nums 中
 */
public class Solution {

    public int getMinDistance(int[] nums, int target, int start) {
        int n = nums.length;
        int res = -1;
        for (int i = 0; start - i >= 0 || start + i < n; i++) {
            if ((start - i >= 0 && nums[start - i] == target) || (start + i < n && nums[start + i] == target)) {
                res = i;
                break;
            }
        }
        return res;
    }

}
