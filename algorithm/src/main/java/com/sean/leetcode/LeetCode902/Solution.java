package com.sean.leetcode.LeetCode902;

import java.util.Arrays;

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

    private String[] digits;
    private char[] s;
    private int[] dp;

    public int atMostNGivenDigitSet1(String[] digits, int n) {
        this.digits = digits;
        s = Integer.toString(n).toCharArray();
        dp = new int[s.length];
        Arrays.fill(dp, -1);
        return dfs(0, true, false);
    }

    private int dfs(int i, boolean isLimit, boolean isNum) {
        if (i == s.length) {
            return isNum ? 1 : 0;//如果填了数字，则为1种合法方案
        }
        if (!isLimit && isNum && dp[i] != -1) {
            return dp[i];//在不受到任何约束的情况下，返回记录的结果，避免重复计算
        }
        int res = 0;
        if (!isNum) {//前面不填数字，那么可以跳过当前位数，也不填数字
            //isLimit改为false,因为没有填数字，位数都比n更短，自然不会受到n的约束
            //isNum仍然为false，因为没有填任何数字
            res = dfs(i + 1, false, false);
        }
        int up = isLimit ? s[i] : '9';//根据是否受到约束，决定可以填的数字的上限
        //注意：对于一般的题目而言，如果此时isNum为false,则必须从1开始枚举，由于本题digits没有0，所以无需处理这种情况
        for (String d : digits) {
            if (d.charAt(0) > up) {
                break;//d超过上限，由于digits是有序的，后面的d都会超过上限，故退出循环
            }
            //isLimit如果当前受到n的约束，且填的数字等于上限，那么后面仍然会受到n的约束
            //isNum为true,因为填了数字
            res += dfs(i + 1, isLimit && d.charAt(0) == up, true);
        }
        if (!isLimit && isNum) {
            dp[i] = res;//在不受到任何约束的情况下，记录结果
        }
        return res;
    }

}
