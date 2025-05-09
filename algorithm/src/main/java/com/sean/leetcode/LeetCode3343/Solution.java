package com.sean.leetcode.LeetCode3343;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-05-09 08:30
 * @Description https://leetcode.cn/problems/count-number-of-balanced-permutations
 * 3343. 统计平衡排列的数目
 * 给你一个字符串 num 。
 * 如果一个数字字符串的奇数位下标的数字之和与偶数位下标的数字之和相等，那么我们称这个数字字符串是 平衡的 。
 * 请你返回 num 不同排列 中，平衡 字符串的数目。
 * 由于答案可能很大，请你将答案对 10^9 + 7 取余 后返回。
 * 一个字符串的 排列 指的是将字符串中的字符打乱顺序后连接得到的字符串。
 * 2 <= num.length <= 80
 * num 中的字符只包含数字 '0' 到 '9' 。
 */
public class Solution {

    private static final int MOD = 1_000_000_007;
    private int[] cnt;
    private int target;
    private int maxOdd;
    private long[][] C;
    private int[] psum;
    private long[][][] dp;

    public int countBalancedPermutations(String num) {
        int sum = 0;
        cnt = new int[10];
        char[] str = num.toCharArray();
        for (char c : str) {
            int cur = c - '0';
            cnt[cur]++;
            sum += cur;
        }
        if ((sum & 1) != 0) {
            return 0;
        }
        target = sum >> 1;
        maxOdd = (str.length + 1) >> 1;
        C = new long[maxOdd + 1][maxOdd + 1];
        for (int i = 0; i <= maxOdd; i++) {
            C[i][0] = 1;
            C[i][i] = 1;
        }
        for (int i = 1; i <= maxOdd; i++) {
            for (int j = 1; j < i; j++) {
                C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]) % MOD;
            }
        }
        psum = new int[11];
        for (int i = 9; i >= 0; i--) {
            psum[i] = psum[i + 1] + cnt[i];
        }
        dp = new long[10][target + 1][maxOdd + 1];
        for (long[][] rows : dp) {
            for (long[] row : rows) {
                Arrays.fill(row, -1);
            }
        }
        return (int) process(0, 0, maxOdd);
    }

    //当前考虑数字i,奇数数字和为cur,剩余奇数位置数oddCnt
    private long process(int pos, int cur, int oddCnt) {
        if (oddCnt < 0 || psum[pos] < oddCnt || cur > target) {
            return 0;
        }
        if (pos > 9) {
            return cur == target && oddCnt == 0 ? 1 : 0;
        }
        if (dp[pos][cur][oddCnt] != -1) {
            return dp[pos][cur][oddCnt];
        }
        int evenCnt = psum[pos] - oddCnt;
        long res = 0;
        for (int i = Math.max(0, cnt[pos] - evenCnt); i <= Math.min(cnt[pos], oddCnt); i++) {
            //当前数字在奇数位填充i位，偶数位填充cnt[pos] - i 位
            long ways = C[oddCnt][i] * C[evenCnt][cnt[pos] - i] % MOD;
            res = (res + ways * process(pos + 1, cur + i * pos, oddCnt - i) % MOD) % MOD;
        }
        dp[pos][cur][oddCnt] = res;
        return res;
    }

}
