package com.sean.leetcode.LeetCode3584;

/**
 * @Author xionghaiyang
 * @Date 2025-06-17 12:32
 * @Description https://leetcode.cn/problems/maximum-product-of-first-and-last-elements-of-a-subsequence/
 * 3584. 子序列首尾元素的最大乘积
 * 给你一个整数数组 nums 和一个整数 m。
 * 返回任意大小为 m 的 子序列 中首尾元素乘积的最大值。
 * 子序列 是可以通过删除原数组中的一些元素（或不删除任何元素），且不改变剩余元素顺序而得到的数组。
 * 1 <= nums.length <= 10^5
 * -10^5 <= nums[i] <= 10^5
 * 1 <= m <= nums.length
 */
public class Solution {

    public long maximumProduct(int[] nums, int m) {
        int n = nums.length;
        int[] max = new int[n];
        int[] min = new int[n];
        max[n - 1] = nums[n - 1];
        min[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            max[i] = Math.max(nums[i], max[i + 1]);
            min[i] = Math.min(nums[i], min[i + 1]);
        }
        long res = Long.MIN_VALUE;
        for (int i = 0; i < n && i + m - 1 < n; i++) {
            if (nums[i] < 0) {
                res = Math.max(res, (long) nums[i] * min[i + m - 1]);
            } else {
                res = Math.max(res, (long) nums[i] * max[i + m - 1]);
            }
        }
        return res;
    }

}
