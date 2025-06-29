package com.sean.leetcode.LeetCode594;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-06-30 05:24
 * @Description https://leetcode.cn/problems/longest-harmonious-subsequence
 * 594. 最长和谐子序列
 * 和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。
 * 给你一个整数数组 nums ，请你在所有可能的 子序列 中找到最长的和谐子序列的长度。
 * 数组的 子序列 是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。
 * 1 <= nums.length <= 2 * 10^4
 * -10^9 <= nums[i] <= 10^9
 */
public class Solution {

    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0;
        for (int left = 0, right = 0; right < n; right++) {
            if (nums[right] - nums[left] > 1) {
                left++;
            }
            if (nums[right] - nums[left] == 1) {
                res = Math.max(res, right - left + 1);
            }
        }
        return res;
    }

}
