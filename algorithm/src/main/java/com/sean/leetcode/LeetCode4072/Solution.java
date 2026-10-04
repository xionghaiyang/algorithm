package com.sean.leetcode.LeetCode4072;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-04 19:09
 * @Description: https://leetcode.cn/problems/maximum-alternating-subarray-sum-with-one-deletion
 * 4072. 一次删除后的最大交替子数组和
 * 给你一个整数数组 nums。
 * 你最多可以从 nums 中删除 一个 元素，然后在剩下数组里选一个 子数组 。
 * 返回所选子数组的最大可能 交替和 。
 * 子数组 是数组中连续的 非空 元素序列。
 * 数组的 交替和 是其偶数下标处元素之和减去奇数下标处元素之和。
 * 在计算其交替和之前，所选子数组会从 0 开始重新编下标。
 * 1 <= nums.length <= 10^5
 * -10^5 <= nums[i] <= 10^5
 */
public class Solution {

    private static final long INF = Long.MIN_VALUE / 2;

    public long maxAlternatingSum(int[] nums) {
        long keepEven = INF, keepOdd = INF, skipEven = INF, skipOdd = INF;
        long res = INF;
        for (int num : nums) {
            //不删当前数字
            long newKeepEven = Math.max(num, keepOdd + num);
            long newKeepOdd = Math.max(INF, keepEven - num);
            //选择删除当前数字
            long deleteSkipEven = keepEven;
            long deleteSkipOdd = keepOdd;
            //已经用过删除机会
            long addSkipEven = Math.max(num, skipOdd + num);
            long addSkipOdd = Math.max(INF, skipEven - num);
            //更新
            keepEven = newKeepEven;
            keepOdd = newKeepOdd;
            skipEven = Math.max(deleteSkipEven, addSkipEven);
            skipOdd = Math.max(deleteSkipOdd, addSkipOdd);
            res = Math.max(res, Math.max(Math.max(keepEven, keepOdd), Math.max(skipEven, skipOdd)));
        }
        return res;
    }

}
