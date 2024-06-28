package com.sean.leetcode.LeetCode1012;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-03-20 08:30
 * @Description: https://leetcode.cn/problems/numbers-with-repeated-digits/
 * 1012. 至少有 1 位重复的数字
 * 给定正整数 n，返回在 [1, n] 范围内具有 至少 1 位 重复数字的正整数的个数。
 */
public class Solution {

    private char[] s;
    private int[][] memo;

    public int numDupDigitsAtMostN(int n) {
        s = Integer.toString(n).toCharArray();
        int m = s.length;
        memo = new int[m][1 << 10];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        return n - process(0, 0, true, false);
    }

    private int process(int i, int mask, boolean isLimit, boolean isNum) {
        if (i == s.length) {
            return isNum ? 1 : 0;//isNum为true表示得到了一个合法数字
        }
        if (!isLimit && isNum && memo[i][mask] != -1) {
            return memo[i][mask];
        }
        int res = 0;
        if (!isNum) {
            res = process(i + 1, mask, false, false);
        }
        int up = isLimit ? s[i] - '0' : 9;//如果前面填的数字都和n的一样，那么这一位至多填数字s[i](否则就超过n)
        //枚举要填入的数字d
        for (int d = isNum ? 0 : 1; d <= up; d++) {
            if (((mask >> d) & 1) == 0) {//d不在mask中
                res += process(i + 1, mask | (1 << d), isLimit && d == up, true);
            }
        }
        if (!isLimit && isNum) {
            memo[i][mask] = res;
        }
        return res;
    }

}
