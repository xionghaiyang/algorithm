package com.sean.leetcode.LeetCode3753;

/**
 * @Author: xionghaiyang
 * @Date: 2026-06-05 05:58
 * @Description: https://leetcode.cn/problems/total-waviness-of-numbers-in-range-ii
 * 3753. 范围内总波动值 II
 * 给你两个整数 num1 和 num2，表示一个 闭 区间 [num1, num2]。.
 * 一个数字的 波动值 定义为该数字中 峰 和 谷 的总数：
 * 如果一个数位 严格大于 其两个相邻数位，则该数位为 峰。
 * 如果一个数位 严格小于 其两个相邻数位，则该数位为 谷。
 * 数字的第一个和最后一个数位 不能 是峰或谷。
 * 任何少于 3 位的数字，其波动值均为 0。
 * 返回范围 [num1, num2] 内所有数字的波动值之和。
 * 1 <= num1 <= num2 <= 10^15
 */
public class Solution {

    public long totalWaviness(long num1, long num2) {
        char[] lowS = String.valueOf(num1).toCharArray();
        char[] highS = String.valueOf(num2).toCharArray();
        int n = highS.length;
        //一个数至多包含n-2个峰或谷
        long[][][][] memo = new long[n][n - 1][3][10];
        return dfs(lowS, highS, memo, 0, 0, 0, 0, true, true);
    }

    private long dfs(char[] lowS, char[] highS, long[][][][] memo, int i, int waviness, int lastCmp, int lastDigit, boolean limitLow, boolean limitHigh) {
        if (i == highS.length) {
            return waviness;
        }
        if (!limitLow && !limitHigh && memo[i][waviness][lastCmp + 1][lastDigit] > 0) {
            return memo[i][waviness][lastCmp + 1][lastDigit] - 1;
        }
        int diffLh = highS.length - lowS.length;
        int low = limitLow && i >= diffLh ? lowS[i - diffLh] - '0' : 0;
        int high = limitHigh ? highS[i] - '0' : 9;
        long res = 0;
        boolean isNum = !limitLow || i > diffLh;
        for (int d = low; d <= high; d++) {
            int cmp = isNum ? Integer.compare(d, lastDigit) : 0;
            int w = waviness + (cmp * lastCmp < 0 ? 1 : 0);
            res += dfs(lowS, highS, memo, i + 1, w, cmp, d, limitLow && d == low, limitHigh && d == high);
        }
        if (!limitLow && !limitHigh) {
            memo[i][waviness][lastCmp + 1][lastDigit] = res + 1;
        }
        return res;
    }

}
