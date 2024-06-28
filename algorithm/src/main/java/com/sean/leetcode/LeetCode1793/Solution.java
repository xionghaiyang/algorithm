package com.sean.leetcode.LeetCode1793;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-19 15:03
 * @Description: https://leetcode.cn/problems/maximum-score-of-a-good-subarray/
 * 1793. 好子数组的最大分数
 * 给你一个整数数组 nums （下标从 0 开始）和一个整数 k 。
 * 一个子数组 (i, j) 的 分数 定义为 min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1) 。
 * 一个 好 子数组的两个端点下标需要满足 i <= k <= j 。
 * 请你返回 好 子数组的最大可能 分数 。
 */
public class Solution {

    public int maximumScore(int[] nums, int k) {
        int n = nums.length;
        int left = k - 1, right = k + 1;
        int res = 0;
        for (int i = nums[k]; ; i--) {
            while (left >= 0 && nums[left] >= i) {
                left--;
            }
            while (right < n && nums[right] >= i) {
                right++;
            }
            res = Math.max(res, (right - left - 1) * i);
            if (left == -1 && right == n) {
                break;
            }
        }
        return res;
    }

    public int maximumScore1(int[] nums, int k) {
        int n = nums.length;
        int left = k - 1, right = k + 1;
        int res = 0;
        for (int i = nums[k]; ; ) {
            while (left >= 0 && nums[left] >= i) {
                left--;
            }
            while (right < n && nums[right] >= i) {
                right++;
            }
            res = Math.max(res, (right - left - 1) * i);
            if (left == -1 && right == n) {
                break;
            }
            i = Math.max((left == -1 ? -1 : nums[left]), (right == n ? -1 : nums[right]));
            if (i == -1) {
                break;
            }
        }
        return res;
    }

}
