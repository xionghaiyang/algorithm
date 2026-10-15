package com.sean.leetcode.LeetCode2916;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-16 06:04
 * @Description: https://leetcode.cn/problems/subarrays-distinct-element-sum-of-squares-ii
 * 2916. 子数组不同元素数目的平方和 II
 * 给你一个下标从 0 开始的整数数组 nums 。
 * 定义 nums 一个子数组的 不同计数 值如下：
 * 令 nums[i..j] 表示 nums 中所有下标在 i 到 j 范围内的元素构成的子数组（满足 0 <= i <= j < nums.length ），那么我们称子数组 nums[i..j] 中不同值的数目为 nums[i..j] 的不同计数。
 * 请你返回 nums 中所有子数组的 不同计数 的 平方 和。
 * 由于答案可能会很大，请你将它对 10^9 + 7 取余 后返回。
 * 子数组指的是一个数组里面一段连续 非空 的元素序列。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 */
public class Solution {

    private static final int MOD = 1_000_000_007;
    private long[] sum;
    private int[] todo;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum = new long[n * 4];
        todo = new int[n * 4];
        long res = 0, s = 0;
        Map<Integer, Integer> last = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            int j = last.getOrDefault(x, 0);
            s += queryAndAdd1(1, 1, n, j + 1, i) * 2 + i - j;
            res = (res + s) % MOD;
            last.put(x, i);
        }
        return (int) res;
    }

    private long queryAndAdd1(int o, int l, int r, int L, int R) {
        if (L <= l && r <= R) {
            long res = sum[o];
            do_(o, l, r, 1);
            return res;
        }
        int m = l + ((r - l) >> 1);
        int add = todo[o];
        if (add != 0) {
            do_(o * 2, l, m, add);
            do_(o * 2 + 1, m + 1, r, add);
            todo[o] = 0;
        }
        long res = 0;
        if (L <= m) {
            res += queryAndAdd1(o * 2, l, m, L, R);
        }
        if (m < R) {
            res += queryAndAdd1(o * 2 + 1, m + 1, r, L, R);
        }
        sum[o] = sum[o * 2] + sum[o * 2 + 1];
        return res;
    }

    private void do_(int o, int l, int r, int add) {
        sum[o] += (long) add * (r - l + 1);
        todo[o] += add;
    }

}
