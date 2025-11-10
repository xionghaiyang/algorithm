package com.sean.leetcode.LeetCode3542;

/**
 * @Author xionghaiyang
 * @Date 2025-11-10 09:44
 * @Description https://leetcode.cn/problems/minimum-operations-to-convert-all-elements-to-zero
 * 3542. 将所有元素变为 0 的最少操作次数
 * 给你一个大小为 n 的 非负 整数数组 nums 。
 * 你的任务是对该数组执行若干次（可能为 0 次）操作，使得 所有 元素都变为 0。
 * 在一次操作中，你可以选择一个子数组 [i, j]（其中 0 <= i <= j < n），将该子数组中所有 最小的非负整数 的设为 0。
 * 返回使整个数组变为 0 所需的最少操作次数。
 * 一个 子数组 是数组中的一段连续元素。
 * 1 <= n == nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 */
public class Solution {

    public int minOperations(int[] nums) {
        int res = 0, top = -1;
        for (int num : nums) {
            while (top >= 0 && num < nums[top]) {
                top--;
                res++;
            }
            if (top < 0 || num != nums[top]) {
                nums[++top] = num;
            }
        }
        return res + top + (nums[0] > 0 ? 1 : 0);
    }

}
