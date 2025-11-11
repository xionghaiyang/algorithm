package com.sean.leetcode.LeetCode474;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-11-11 12:13
 * @Description https://leetcode.cn/problems/ones-and-zeroes
 * 474. 一和零
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] 仅由 '0' 和 '1' 组成
 * 1 <= m, n <= 100
 */
public class Solution {

    public int findMaxForm(String[] strs, int m, int n) {
        int k = strs.length;
        int[] cnt0 = new int[k];
        for (int i = 0; i < k; i++) {
            cnt0[i] = (int) strs[i].chars().filter(c -> c == '0').count();
        }
        int[][][] memo = new int[k][m + 1][n + 1];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j <= m; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        return dfs(strs, cnt0, memo, k - 1, m, n);
    }

    private int dfs(String[] strs, int[] cnt0, int[][][] memo, int i, int j, int k) {
        if (i < 0) {
            return 0;
        }
        if (memo[i][j][k] != -1) {
            return memo[i][j][k];
        }
        int res = dfs(strs, cnt0, memo, i - 1, j, k);
        int cnt1 = strs[i].length() - cnt0[i];
        if (j >= cnt0[i] && k >= cnt1) {
            res = Math.max(res, dfs(strs, cnt0, memo, i - 1, j - cnt0[i], k - cnt1) + 1);
        }
        return memo[i][j][k] = res;
    }

}
