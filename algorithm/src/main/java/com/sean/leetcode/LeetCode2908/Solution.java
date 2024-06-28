package com.sean.leetcode.LeetCode2908;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-29 13:48
 * @Description: https://leetcode.cn/problems/minimum-sum-of-mountain-triplets-i/
 * 2908. 元素和最小的山形三元组 I
 * 给你一个下标从 0 开始的整数数组 nums 。
 * 如果下标三元组 (i, j, k) 满足下述全部条件，则认为它是一个 山形三元组 ：
 * i < j < k
 * nums[i] < nums[j] 且 nums[k] < nums[j]
 * 请你找出 nums 中 元素和最小 的山形三元组，并返回其 元素和 。如果不存在满足条件的三元组，返回 -1 。
 */
public class Solution {

    public int minimumSum(int[] nums) {
        int n = nums.length;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] < nums[j] && nums[k] < nums[j]) {
                        res = Math.min(res, nums[i] + nums[j] + nums[k]);
                    }
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public int minimumSum1(int[] nums) {
        int n = nums.length;
        int res = Integer.MAX_VALUE, mn = Integer.MAX_VALUE;
        int[] left = new int[n];
        for (int i = 1; i < n; i++) {
            left[i] = mn = Math.min(nums[i - 1], mn);
        }
        int right = nums[n - 1];
        for (int i = n - 2; i > 0; i--) {
            if (left[i] < nums[i] && nums[i] > right) {
                res = Math.min(res, left[i] + nums[i] + right);
            }
            right = Math.min(right, nums[i]);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

}
