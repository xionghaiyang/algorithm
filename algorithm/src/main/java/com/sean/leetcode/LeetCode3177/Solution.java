package com.sean.leetcode.LeetCode3177;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-09-07 15:16
 * @Description https://leetcode.cn/problems/find-the-maximum-length-of-a-good-subsequence-ii
 * 3177. 求出最长好子序列 II
 * 给你一个整数数组 nums 和一个 非负 整数 k 。
 * 如果一个整数序列 seq 满足在范围下标范围 [0, seq.length - 2] 中存在 不超过 k 个下标 i 满足 seq[i] != seq[i + 1] ，
 * 那么我们称这个整数序列为 好 序列。
 * 请你返回 nums 中 好子序列的最长长度.
 * 1 <= nums.length <= 5 * 10^3
 * 1 <= nums[i] <= 10^9
 * 0 <= k <= min(50, nums.length)
 */
public class Solution {

    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, int[]> dp = new HashMap<>();
        int[] zd = new int[k + 1];
        for (int i = 0; i < n; i++) {
            int v = nums[i];
            dp.putIfAbsent(v, new int[k + 1]);
            int[] tmp = dp.get(v);
            for (int j = 0; j <= k; j++) {
                tmp[j]++;
                if (j > 0) {
                    tmp[j] = Math.max(tmp[j], zd[j - 1] + 1);
                }
            }
            for (int j = 0; j <= k; j++) {
                zd[j] = Math.max(zd[j], tmp[j]);
            }
        }
        return zd[k];
    }

}
