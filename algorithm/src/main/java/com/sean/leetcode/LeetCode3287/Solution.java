package com.sean.leetcode.LeetCode3287;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2025-01-18 19:53
 * @Description https://leetcode.cn/problems/find-the-maximum-sequence-value-of-array/
 * 3287. 求出数组中最大序列值
 * 给你一个整数数组 nums 和一个 正 整数 k 。
 * 定义长度为 2 * x 的序列 seq 的 值 为：
 * (seq[0] OR seq[1] OR ... OR seq[x - 1]) XOR (seq[x] OR seq[x + 1] OR ... OR seq[2 * x - 1]).
 * 请你求出 nums 中所有长度为 2 * k 的子序列的 最大值 。
 * 2 <= nums.length <= 400
 * 1 <= nums[i] < 2^7
 * 1 <= k <= nums.length / 2
 */
public class Solution {

    public int maxValue(int[] nums, int k) {
        List<Set<Integer>> A = findORs(nums, k);
        List<Set<Integer>> B = findORs(reverse(nums), k);
        int max = 0;
        int n = nums.length;
        for (int i = k - 1; i < n - k; i++) {
            for (int a : A.get(i)) {
                for (int b : B.get(n - i - 2)) {
                    max = Math.max(max, a ^ b);
                }
            }
        }
        return max;
    }

    private List<Set<Integer>> findORs(int[] nums, int k) {
        List<Set<Integer>> dp = new ArrayList<>();
        List<Set<Integer>> prev = new ArrayList<>();
        for (int i = 0; i <= k; i++) {
            prev.add(new HashSet<>());
        }
        prev.get(0).add(0);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = Math.min(k - 1, i + 1); j >= 0; j--) {
                for (int x : prev.get(j)) {
                    prev.get(j + 1).add(x | nums[i]);
                }
            }
            dp.add(new HashSet<>(prev.get(k)));
        }
        return dp;
    }

    private int[] reverse(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = nums[n - 1 - i];
        }
        return res;
    }

}
