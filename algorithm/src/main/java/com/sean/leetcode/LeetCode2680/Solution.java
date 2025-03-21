package com.sean.leetcode.LeetCode2680;

/**
 * @Author xionghaiyang
 * @Date 2025-03-21 09:36
 * @Description https://leetcode.cn/problems/maximum-or
 * 2680. 最大或值
 * 给你一个下标从 0 开始长度为 n 的整数数组 nums 和一个整数 k 。
 * 每一次操作中，你可以选择一个数并将它乘 2 。
 * 你最多可以进行 k 次操作，请你返回 nums[0] | nums[1] | ... | nums[n - 1] 的最大值。
 * a | b 表示两个整数 a 和 b 的 按位或 运算。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= 15
 */
public class Solution {

    public long maximumOr(int[] nums, int k) {
        int n = nums.length;
        //suf[i]表示nums[i+1]到nums[n-1]的OR
        int[] suf = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            suf[i] = suf[i + 1] | nums[i + 1];
        }
        long res = 0;
        int pre = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, pre | ((long) nums[i] << k) | suf[i]);
            pre |= nums[i];
        }
        return res;
    }

    public long maximumOr1(int[] nums, int k) {
        int all0r = 0;
        int fixed = 0;
        for (int x : nums) {
            fixed |= all0r & x;
            all0r |= x;
        }
        long res = 0;
        for (int x : nums) {
            res = Math.max(res, (all0r ^ x) | fixed | ((long) x << k));
        }
        return res;
    }

}
