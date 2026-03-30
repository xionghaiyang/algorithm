package com.sean.leetcode.LeetCode115;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-03-30 12:20
 * @Description https://leetcode.cn/problems/distinct-subsequences
 * 115. 不同的子序列
 * 给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数。
 * 测试用例保证结果在 32 位有符号整数范围内。
 * 1 <= s.length, t.length <= 1000
 * s 和 t 由英文字母组成
 */
public class Solution {

    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        process(s.toCharArray(), t.toCharArray(), memo, m - 1, n - 1);
        return memo[m - 1][n - 1];
    }

    private int process(char[] s, char[] t, int[][] memo, int i, int j) {
        if (i < 0) {
            return j < 0 ? 1 : 0;
        }
        if (j < 0) {
            return 1;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int res = process(s, t, memo, i - 1, j);
        if (s[i] == t[j]) {
            res += process(s, t, memo, i - 1, j - 1);
        }
        memo[i][j] = res;
        return res;
    }

}
