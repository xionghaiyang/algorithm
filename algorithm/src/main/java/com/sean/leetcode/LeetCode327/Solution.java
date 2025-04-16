package com.sean.leetcode.LeetCode327;

/**
 * @Author xionghaiyang
 * @Date 2025-04-16 21:31
 * @Description https://leetcode.cn/problems/count-of-range-sum
 * 327. 区间和的个数
 * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。
 * 求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 * -10^5 <= lower <= upper <= 10^5
 * 题目数据保证答案是一个 32 位 的整数
 */
public class Solution {

    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        long[] preSum = new long[n];
        preSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        return process(preSum, 0, n - 1, lower, upper);
    }

    private int process(long[] preSum, int left, int right, int lower, int upper) {
        if (left == right) {
            return preSum[left] >= lower && preSum[left] <= upper ? 1 : 0;
        }
        int mid = left + ((right - left) >> 1);
        return process(preSum, left, mid, lower, upper) + process(preSum, mid + 1, right, lower, upper) + merge(preSum, left, mid, right, lower, upper);
    }

    private int merge(long[] preSum, int left, int mid, int right, int lower, int upper) {
        int res = 0;
        int windowLeft = left;
        int windowRight = left;
        //[windowLeft,windowRight)
        for (int i = mid + 1; i <= right; i++) {
            long min = preSum[i] - upper;
            long max = preSum[i] - lower;
            while (windowRight <= mid && preSum[windowRight] <= max) {
                windowRight++;
            }
            while (windowLeft <= mid && preSum[windowLeft] < min) {
                windowLeft++;
            }
            res += windowRight - windowLeft;
        }
        long[] help = new long[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            help[i++] = preSum[p1] <= preSum[p2] ? preSum[p1++] : preSum[p2++];
        }
        while (p1 <= mid) {
            help[i++] = preSum[p1++];
        }
        while (p2 <= right) {
            help[i++] = preSum[p2++];
        }
        for (i = 0; i < help.length; i++) {
            preSum[left + i] = help[i];
        }
        return res;
    }

}
