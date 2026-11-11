package com.sean.leetcode.LeetCode1745;

/**
 * @Author xionghaiyang
 * @Date 2025-03-04 08:24
 * @Description https://leetcode.cn/problems/palindrome-partitioning-iv
 * 1745. 分割回文串 IV
 * 给你一个字符串 s ，如果可以将它分割成三个 非空 回文子字符串，那么返回 true ，否则返回 false 。
 * 当一个字符串正着读和反着读是一模一样的，就称其为 回文字符串 。
 * 3 <= s.length <= 2000
 * s​​​​​​ 只包含小写英文字母。
 */
public class Solution {

    public boolean checkPartitioning(String s) {
        return palindromePartition(s, 3) == 0;
    }

    private int palindromePartition(String s, int k) {
        char[] str = s.toCharArray();
        int n = str.length;
        int[][] minChange = new int[n][n];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                minChange[i][j] = minChange[i + 1][j - 1] + (str[i] != str[j] ? 1 : 0);
            }
        }
        int[] f = minChange[0];
        for (int i = 1; i < k; i++) {
            for (int right = n - k + i; right >= i; right--) {
                f[right] = Integer.MAX_VALUE;
                for (int left = i; left <= right; left++) {
                    f[right] = Math.min(f[right], f[left - 1] + minChange[left][right]);
                }
            }
        }
        return f[n - 1];
    }

}
