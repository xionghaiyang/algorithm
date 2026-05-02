package com.sean.leetcode.LeetCode2376;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-16 12:57
 * @Description: https://leetcode.cn/problems/count-special-integers
 * 2376. 统计特殊整数
 * 如果一个正整数每一个数位都是 互不相同 的，我们称它是 特殊整数 。
 * 给你一个 正 整数 n ，请你返回区间 [1, n] 之间特殊整数的数目。
 * 1 <= n <= 2 * 10^9
 */
public class Solution {

    public int countSpecialNumbers(int n) {
        char[] s = Integer.toString(n).toCharArray();
        int m = s.length;
        int[][] memo = new int[m][1 << 10];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        return process(s, memo, 0, 0, true, false);
    }

    private int process(char[] s, int[][] memo, int i, int mask, boolean isLimit, boolean isNum) {
        if (i == s.length) {
            return isNum ? 1 : 0;
        }
        if (!isLimit && isNum && memo[i][mask] != -1) {
            return memo[i][mask];
        }
        int res = 0;
        if (!isNum) {
            res += process(s, memo, i + 1, mask, false, false);
        }
        for (int d = isNum ? 0 : 1, up = isLimit ? s[i] - '0' : 9; d <= up; d++) {
            if ((mask & (1 << d)) == 0) {
                res += process(s, memo, i + 1, mask | (1 << d), isLimit && d == up, true);
            }
        }
        if (!isLimit && isNum) {
            memo[i][mask] = res;
        }
        return res;
    }

}
