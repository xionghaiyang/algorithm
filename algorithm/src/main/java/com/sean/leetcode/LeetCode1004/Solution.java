package com.sean.leetcode.LeetCode1004;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-30 13:39
 * @Description: https://leetcode.cn/problems/max-consecutive-ones-iii/?envType=study-plan-v2&envId=leetcode-75
 * 1004. 最大连续1的个数 III
 * 给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
 */
public class Solution {

    public int longestOnes1(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = 0;
        while (right < n) {
            if (nums[right++] == 0) {
                k--;
            }
            if (k < 0 && nums[left++] == 0) {
                k++;
            }
        }
        return right - left;
    }

    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int left = 0, lsum = 0, rsum = 0;
        int res = 0;
        for (int right = 0; right < n; right++) {
            rsum += 1 - nums[right];
            while (lsum < rsum - k) {
                lsum += 1 - nums[left];
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

}
