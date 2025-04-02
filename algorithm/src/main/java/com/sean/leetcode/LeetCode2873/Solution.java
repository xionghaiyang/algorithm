package com.sean.leetcode.LeetCode2873;

/**
 * @Author xionghaiyang
 * @Date 2025-04-02 09:33
 * @Description https://leetcode.cn/problems/maximum-value-of-an-ordered-triplet-i
 * 2873. 有序三元组中的最大值 I
 * 给你一个下标从 0 开始的整数数组 nums 。
 * 请你从所有满足 i < j < k 的下标三元组 (i, j, k) 中，找出并返回下标三元组的最大值。
 * 如果所有满足条件的三元组的值都是负数，则返回 0 。
 * 下标三元组 (i, j, k) 的值等于 (nums[i] - nums[j]) * nums[k] 。
 * 3 <= nums.length <= 100
 * 1 <= nums[i] <= 10^6
 */
public class Solution {

    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        //位置i左侧（不包含i）的最大值是多少，没有左侧元素则值为-1
        int[] leftMax = new int[n];
        leftMax[0] = -1;
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], nums[i - 1]);
        }
        //位置i右侧元素(不包含i)的最大值是多少，没有右侧元素则值为-1
        int[] rightMax = new int[n];
        rightMax[n - 1] = -1;
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i + 1]);
        }
        long res = 0;
        for (int i = 1; i < n - 1; i++) {
            res = Math.max(res, (long) (leftMax[i] - nums[i]) * (long) rightMax[i]);
        }
        return res;
    }

    public long maximumTripletValue1(int[] nums) {
        int n = nums.length;
        long res = 0, iMax = 0, diffMax = 0;
        for (int k = 0; k < n; k++) {
            res = Math.max(res, diffMax * nums[k]);
            diffMax = Math.max(diffMax, iMax - nums[k]);
            iMax = Math.max(iMax, nums[k]);
        }
        return res;
    }

}
