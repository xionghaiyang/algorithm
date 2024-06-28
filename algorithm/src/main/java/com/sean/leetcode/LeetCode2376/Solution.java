package com.sean.leetcode.LeetCode2376;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-16 12:57
 * @Description: https://leetcode.cn/problems/count-special-integers/
 * 2376. 统计特殊整数
 * 如果一个正整数每一个数位都是 互不相同 的，我们称它是 特殊整数 。
 * 给你一个 正 整数 n ，请你返回区间 [1, n] 之间特殊整数的数目。
 */
public class Solution {

    private char[] s;
    private int[][] memo;

    public int countSpecialNumbers(int n) {
        s = Integer.toString(n).toCharArray();
        int m = s.length;
        memo = new int[m][1 << 10];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        return process(0, 0, true, false);
    }

    private int process(int i, int mask, boolean isLimit, boolean isNum) {
        if (i == s.length) {
            return isNum ? 1 : 0;//isNum为true表示得到了一个合法数字
        }
        if (!isLimit && isNum && memo[i][mask] != -1) {
            return memo[i][mask];
        }
        int res = 0;
        if (!isNum) {//可以跳过当前数位
            res += process(i + 1, mask, false, false);
        }
        int up = isLimit ? s[i] - '0' : 9;//如果前面填的数字都和n的一样，那么这一位至多填数字s[i]（否则就超过n了）
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
