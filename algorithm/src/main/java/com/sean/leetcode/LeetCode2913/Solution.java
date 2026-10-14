package com.sean.leetcode.LeetCode2913;

import java.util.*;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-15 05:57
 * @Description: https://leetcode.cn/problems/subarrays-distinct-element-sum-of-squares-i
 * 2913. 子数组不同元素数目的平方和 I
 * 你一个下标从 0 开始的整数数组 nums 。
 * 定义 nums 一个子数组的 不同计数 值如下：
 * 令 nums[i..j] 表示 nums 中所有下标在 i 到 j 范围内的元素构成的子数组（满足 0 <= i <= j < nums.length ），那么我们称子数组 nums[i..j] 中不同值的数目为 nums[i..j] 的不同计数。
 * 请你返回 nums 中所有子数组的 不同计数 的 平方 和。
 * 子数组指的是一个数组里面一段连续 非空 的元素序列。
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 */
public class Solution {

    public int sumCounts(List<Integer> nums) {
        int res = 0, size = nums.size();
        for (int i = 0; i < size; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < size; j++) {
                set.add(nums.get(j));
                res += set.size() * set.size();
            }
        }
        return res;
    }

    private long[] sum;
    private int[] todo;

    public int sumCounts1(List<Integer> nums) {
        int n = nums.size();
        sum = new long[n * 4];
        todo = new int[n * 4];
        long res = 0, s = 0;
        Map<Integer, Integer> last = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int x = nums.get(i - 1);
            int j = last.getOrDefault(x, 0);
            s += queryAndAdd1(1, 1, n, j + 1, i) * 2 + i - j;
            res += s;
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
        int mid = l + ((r - l) >> 1);
        int add = todo[o];
        if (add != 0) {
            do_(o * 2, l, mid, add);
            do_(o * 2 + 1, mid + 1, r, add);
            todo[o] = 0;
        }
        long res = 0;
        if (L <= mid) {
            res += queryAndAdd1(o * 2, l, mid, L, R);
        }
        if (mid < R) {
            res += queryAndAdd1(o * 2 + 1, mid + 1, r, L, R);
        }
        sum[o] = sum[o * 2] + sum[o * 2 + 1];
        return res;
    }

    private void do_(int o, int l, int r, int add) {
        sum[o] += (long) add * (r - l + 1);
        todo[o] += add;
    }

}
