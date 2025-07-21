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

    private char[] s;
    private int[][] memo;
    private int m;

    public int countSpecialNumbers(int n) {
        s = Integer.toString(n).toCharArray();
        m = s.length;
        memo = new int[m][1 << 10];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        return process(0, 0, true, false);
    }

    private int process(int i, int mask, boolean isLimit, boolean isNum) {
        if (i == m) {
            //isNum为true表示得到了一个合法数字
            return isNum ? 1 : 0;
        }
        if (!isLimit && isNum && memo[i][mask] != -1) {
            return memo[i][mask];
        }
        int res = 0;
        //可以跳过当前数位
        if (!isNum) {
            res += process(i + 1, mask, false, false);
        }
        //如果前面填的数字都和n一样，那么这一位至多填数字s[i](否则就超过n了)
        int up = isLimit ? s[i] - '0' : 9;
        //枚举要填入的数字d
        for (int d = isNum ? 0 : 1; d <= up; d++) {
            //d不在mask中
            if ((mask & (1 << d)) == 0) {
                res += process(i + 1, mask | (1 << d), isLimit && d == up, true);
            }
        }
        if (!isLimit && isNum) {
            memo[i][mask] = res;
        }
        return res;
    }

}
