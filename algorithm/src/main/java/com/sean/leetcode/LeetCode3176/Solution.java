package com.sean.leetcode.LeetCode3176;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-09-07 12:22
 * @Description https://leetcode.cn/problems/find-the-maximum-length-of-a-good-subsequence-i/description/
 * 3176. 求出最长好子序列 I
 * 给你一个整数数组 nums 和一个 非负 整数 k 。
 * 如果一个整数序列 seq 满足在下标范围 [0, seq.length - 2] 中 最多只有 k 个下标 i 满足 seq[i] != seq[i + 1] ，
 * 那么我们称这个整数序列为 好 序列。
 * 请你返回 nums 中 好子序列的最长长度。
 * 1 <= nums.length <= 500
 * 1 <= nums[i] <= 10^9
 * 0 <= k <= min(nums.length, 25)
 */
public class Solution {

    public int maximumLength(int[] nums, int k) {
        int res = 0;
        int n = nums.length;
        //dp[i][j]表示以num[i]结尾组成的最长合法序列中有j个数字与其在序列中的后一个数字不相等
        int[][] dp = new int[n][51];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
            for (int k0 = 0; k0 <= k; k0++) {
                for (int j = 0; j < i; j++) {
                    int add = nums[i] != nums[j] ? 1 : 0;
                    if (k0 - add >= 0 && dp[j][k0 - add] != -1) {
                        dp[i][k0] = Math.max(dp[i][k0], dp[j][k0 - add] + 1);
                    }
                }
                res = Math.max(res, dp[i][k0]);
            }
        }
        return res;
    }

    public int maximumLength1(int[] nums, int k) {
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
                if (j > 0) {
                    zd[j] = Math.max(zd[j], zd[j - 1]);
                }
            }
        }
        return zd[k];
    }

}
