package com.sean.leetcode.LeetCodeInterview0814;

/**
 * @Author xionghaiyang
 * @Date 2026-03-18 21:20
 * @Description https://leetcode.cn/problems/boolean-evaluation-lcci
 * 面试题 08.14. 布尔运算
 * 给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。
 * 实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
 * 运算符的数量不超过 19 个
 */
public class Solution {

    public int countEval(String s, int result) {
        int n = s.length();
        int[][][] dp = new int[n][n][2];
        for (int i = 0; i < n; i += 2) {
            char c = s.charAt(i);
            dp[i][i][c - '0'] = 1;
        }
        for (int len = 3; len <= n; len += 2) {
            for (int left = 0; left + len <= n; left += 2) {
                int right = left + len - 1;
                for (int mid = left + 1; mid < right; mid += 2) {
                    switch (s.charAt(mid)) {
                        case '&':
                            dp[left][right][0] += dp[left][mid - 1][0] * dp[mid + 1][right][0]; // 0 & 0 = 0
                            dp[left][right][0] += dp[left][mid - 1][1] * dp[mid + 1][right][0]; // 1 & 0 = 0
                            dp[left][right][0] += dp[left][mid - 1][0] * dp[mid + 1][right][1]; // 0 & 1 = 0
                            dp[left][right][1] += dp[left][mid - 1][1] * dp[mid + 1][right][1]; // 1 & 1 = 1
                            break;
                        case '|':
                            dp[left][right][0] += dp[left][mid - 1][0] * dp[mid + 1][right][0]; // 0 | 0 = 0
                            dp[left][right][1] += dp[left][mid - 1][1] * dp[mid + 1][right][0]; // 1 | 0 = 1
                            dp[left][right][1] += dp[left][mid - 1][0] * dp[mid + 1][right][1]; // 0 | 1 = 1
                            dp[left][right][1] += dp[left][mid - 1][1] * dp[mid + 1][right][1]; // 1 | 1 = 1
                            break;
                        case '^':
                            dp[left][right][0] += dp[left][mid - 1][0] * dp[mid + 1][right][0]; // 0 ^ 0 = 0
                            dp[left][right][1] += dp[left][mid - 1][1] * dp[mid + 1][right][0]; // 1 ^ 0 = 1
                            dp[left][right][1] += dp[left][mid - 1][0] * dp[mid + 1][right][1]; // 0 ^ 1 = 1
                            dp[left][right][0] += dp[left][mid - 1][1] * dp[mid + 1][right][1]; // 1 ^ 1 = 0
                            break;
                    }
                }
            }
        }
        return dp[0][n - 1][result];
    }

}
