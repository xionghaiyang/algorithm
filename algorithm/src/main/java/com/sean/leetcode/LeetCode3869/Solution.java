package com.sean.leetcode.LeetCode3869;

/**
 * @Author xionghaiyang
 * @Date 2026-03-28 19:21
 * @Description https://leetcode.cn/problems/count-fancy-numbers-in-a-range
 * 3869. 统计区间内奇妙数的数目
 * 给你两个整数 l 和 r。
 * 如果一个整数的数位形成一个 严格单调 序列，即数位是 严格递增 或 严格递减 的，那么这个整数被称为 好数。
 * 所有一位数都被认为是好数。
 * 如果一个整数是好数，或者它的 数位和 是好数，那么这个整数被称为 奇妙数。
 * 返回一个整数，表示在区间 [l, r]（包含边界）内的奇妙数的数量。
 * 如果一个序列中的每个元素都 严格大于 其前一个元素（如果存在），则该序列被称为 严格递增。
 * 如果一个序列中的每个元素都 严格小于 其前一个元素（如果存在），则该序列被称为 严格递减。
 * 1 <= l <= r <= 10^15
 */
public class Solution {

    //已经填了至多一个数（不含前导零）
    private static final int STATE_INIT = 0;
    //已填数字是严格递增的
    private static final int STATE_INC = 1;
    //已填数字是严格递减的
    private static final int STATE_DEC = 2;
    //已填数字不是好数
    private static final int STATE_NOT_GOOD = 3;

    public long countFancy(long l, long r) {
        char[] lowS = String.valueOf(l).toCharArray();
        char[] highS = String.valueOf(r).toCharArray();
        int n = highS.length;
        //数位最大和为9n
        long[][][][] memo = new long[n][n * 9 + 1][10][4];
        return dfs(0, 0, 0, STATE_INIT, true, true, lowS, highS, memo);
    }

    private long dfs(int i, int digitSum, int prev, int state, boolean limitLow, boolean limitHigh
            , char[] lowS, char[] highS, long[][][][] memo) {
        if (i == highS.length) {
            return state != STATE_NOT_GOOD || isGood(digitSum) ? 1 : 0;
        }
        if (!limitLow && !limitHigh && memo[i][digitSum][prev][state] > 0) {
            return memo[i][digitSum][prev][state] - 1;
        }
        int diffLength = highS.length - lowS.length;
        int low = limitLow && i >= diffLength ? lowS[i - diffLength] - '0' : 0;
        int high = limitHigh ? highS[i] - '0' : 9;
        long res = 0;
        int d = low;
        if (limitLow && i < diffLength) {
            //不填数字，上界不受约束
            res = dfs(i + 1, 0, 0, STATE_INIT, true, false, lowS, highS, memo);
            d = 1;
        }
        for (; d <= high; d++) {
            int newState = state;
            switch (state) {
                case STATE_INIT:
                    //之前填过数
                    if (prev > 0) {
                        if (d > prev) {
                            newState = STATE_INC;
                        } else if (d < prev) {
                            newState = STATE_DEC;
                        } else {
                            newState = STATE_NOT_GOOD;
                        }
                    }
                    break;
                case STATE_INC:
                    if (d <= prev) {
                        newState = STATE_NOT_GOOD;
                    }
                    break;
                case STATE_DEC:
                    if (d >= prev) {
                        newState = STATE_NOT_GOOD;
                    }
                    break;
            }
            res += dfs(i + 1, digitSum + d, d, newState,
                    limitLow && d == low
                    , limitHigh && d == high
                    , lowS, highS, memo);
        }
        if (!limitLow && !limitHigh) {
            memo[i][digitSum][prev][state] = res + 1;
        }
        return res;
    }

    //判断数位和s是否为好数
    private boolean isGood(int s) {
        //s是一位数或者两位数
        if (s < 100) {
            //十位和个位不相等
            return s / 10 != s % 10;
        }
        //s是三位数，其百位一定是1，只能严格递增
        return 1 < s / 10 % 10 && s / 10 % 10 < s % 10;
    }

}
