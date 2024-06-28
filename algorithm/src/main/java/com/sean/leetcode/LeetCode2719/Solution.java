package com.sean.leetcode.LeetCode2719;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-16 09:32
 * @Description: https://leetcode.cn/problems/count-of-integers/
 * 2719. 统计整数数目
 * 给你两个数字字符串 num1 和 num2 ，以及两个整数 max_sum 和 min_sum 。
 * 如果一个整数 x 满足以下条件，我们称它是一个好整数：
 * num1 <= x <= num2
 * min_sum <= digit_sum(x) <= max_sum.
 * 请你返回好整数的数目。答案可能很大，请返回答案对 10^9 + 7 取余后的结果。
 * 注意，digit_sum(x) 表示 x 各位数字之和。
 */
public class Solution {

    private static final int MOD = 1_000_000_007;
    private int minSum;
    private int maxSum;

    public int count(String num1, String num2, int minSum, int maxSum) {
        this.minSum = minSum;
        this.maxSum = maxSum;
        int res = calc(num2) - calc(num1) + MOD;
        int sum = 0;
        for (char c : num1.toCharArray()) {
            sum += c - '0';
        }
        if (minSum <= sum && sum <= maxSum) {
            res++;
        }
        return res % MOD;
    }

    private int calc(String s) {
        int n = s.length();
        int[][] memo = new int[n][Math.min(9 * n, maxSum) + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(0, 0, true, s.toCharArray(), memo);
    }

    private int dfs(int i, int sum, boolean isLimit, char[] s, int[][] memo) {
        if (sum > maxSum) {
            return 0;
        }
        if (i == s.length) {
            return sum >= minSum ? 1 : 0;
        }
        if (!isLimit && memo[i][sum] != -1) {
            return memo[i][sum];
        }
        int up = isLimit ? s[i] - '0' : 9;
        int res = 0;
        for (int d = 0; d <= up; d++) {//枚举当前数位填d
            res = (res + dfs(i + 1, sum + d, isLimit && d == up, s, memo)) % MOD;
        }
        if (!isLimit) {
            memo[i][sum] = res;
        }
        return res;
    }

    public int count1(String num1, String num2, int minSum, int maxSum) {
        int n = num2.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n - num1.length(); i++) {
            builder.append("0");
        }
        num1 = builder.append(num1).toString(); //补前导零，和num2对齐
        int[][] memo = new int[n][Math.min(9 * n, maxSum) + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return process(0, 0, true, true, num1.toCharArray(), num2.toCharArray(), minSum, maxSum, memo);
    }

    private int process(int i, int sum, boolean limitLow, boolean limitHigh, char[] num1, char[] num2, int minSum, int maxSum, int[][] memo) {
        if (sum > maxSum) {
            return 0;
        }
        if (i == num2.length) {
            return sum >= minSum ? 1 : 0;
        }
        if (!limitLow && !limitHigh && memo[i][sum] != -1) {
            return memo[i][sum];
        }
        int low = limitLow ? num1[i] - '0' : 0;
        int high = limitHigh ? num2[i] - '0' : 9;
        int res = 0;
        for (int d = low; d <= high; d++) {
            res = (res + process(i + 1, sum + d, limitLow && d == low, limitHigh && d == high, num1, num2, minSum, maxSum, memo)) % 1_000_000_007;
        }
        if (!limitLow && !limitHigh) {
            memo[i][sum] = res;
        }
        return res;
    }

}
