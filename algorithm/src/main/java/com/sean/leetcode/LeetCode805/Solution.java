package com.sean.leetcode.LeetCode805;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-14 08:26
 * @Description: https://leetcode.cn/problems/split-array-with-same-average/
 * 805. 数组的均值分割
 * 给定你一个整数数组 nums
 * 我们要将 nums 数组中的每个元素移动到 A 数组 或者 B 数组中，使得 A 数组和 B 数组不为空，并且 average(A) == average(B) 。
 * 如果可以完成则返回true ， 否则返回 false  。
 * 注意：对于数组 arr ,  average(arr) 是 arr 的所有元素除以 arr 长度的和。
 */
public class Solution {

    public boolean splitArraySameAverage(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        int n = nums.length, m = n / 2;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        boolean isPossible = false;
        for (int i = 1; i <= m; i++) {
            if (sum * i % n == 0) {
                isPossible = true;
                break;
            }
        }
        if (!isPossible) {
            return false;
        }
        Set<Integer>[] dp = new Set[m + 1];
        for (int i = 0; i <= m; i++) {
            dp[i] = new HashSet<Integer>();
        }
        dp[0].add(0);
        for (int num : nums) {
            for (int i = m; i >= 1; i--) {
                for (int x : dp[i - 1]) {
                    int cur = x + num;
                    if (cur * n == sum * i) {
                        return true;
                    }
                    dp[i].add(cur);
                }
            }
        }
        return false;
    }

}
