package com.sean.leetcode.LeetCode902;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-18 08:15
 * @Description: https://leetcode.cn/problems/numbers-at-most-n-given-digit-set/
 * 902. 最大为 N 的数字组合
 * 给定一个按 非递减顺序 排列的数字数组 digits 。
 * 你可以用任意次数 digits[i] 来写的数字。
 * 例如，如果 digits = ['1','3','5']，我们可以写数字，如 '13', '551', 和 '1351315'。
 * 返回 可以生成的小于或等于给定整数 n 的正整数的个数 。
 */
public class Solution {

    public int atMostNGivenDigitSet(String[] digits, int n) {
        String s = Integer.toString(n);
        int m = digits.length, k = s.length();
        int[][] dp = new int[k + 1][2];
        //dp[i][0]表示由digits构成且小于n的前i位的数字的个数
        //dp[i][1]表示由digits构成且等于n的前i位的数字的个数
        dp[0][1] = 1;
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < m; j++) {
                if (digits[j].charAt(0) == s.charAt(i - 1)) {
                    dp[i][1] = dp[i - 1][1];
                } else if (digits[j].charAt(0) < s.charAt(i - 1)) {
                    dp[i][0] += dp[i - 1][1];
                } else {
                    break;
                }
            }
            if (i > 1) {
                dp[i][0] += m + dp[i - 1][0] * m;
            }
        }
        return dp[k][0] + dp[k][1];
    }

}
