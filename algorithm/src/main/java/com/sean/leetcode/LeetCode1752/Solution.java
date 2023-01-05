package com.sean.leetcode.LeetCode1752;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-27 21:45
 * @Description: https://leetcode.cn/problems/check-if-array-is-sorted-and-rotated/
 * 1752. 检查数组是否经排序和轮转得到
 * 给你一个数组 nums 。
 * nums 的源数组中，所有元素与 nums 相同，但按非递减顺序排列。
 * 如果 nums 能够由源数组轮转若干位置（包括 0 个位置）得到，则返回 true ；否则，返回 false 。
 * 源数组中可能存在 重复项 。
 * 注意：我们称数组 A 在轮转 x 个位置后得到长度相同的数组 B ，当它们满足 A[i] == B[(i+x) % A.length] ，其中 % 为取余运算。
 */
public class Solution {

    public boolean check(int[] nums) {
        int n = nums.length;
        int index = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                index = i;
                break;
            }
        }
        if (index == 0) {
            return true;
        }
        for (int i = index + 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                return false;
            }
        }
        return nums[0] >= nums[n - 1];
    }

}
