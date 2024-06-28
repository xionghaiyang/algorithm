package com.sean.leetcode;

/**
 * 回文子串
 * https://leetcode-cn.com/problems/palindromic-substrings/
 */
public class LeetCode647 {

    public static int countSubstrings1(String s) {
        int length = s.length();
        int size = 2 * length - 1;
        int res = 0;
        for (int i = 0; i < size; i++) {
            int left = i / 2;
            int right = left + i % 2;
            while (left >= 0 && right < length && s.charAt(left--) == s.charAt(right++)) {
                res++;
            }
        }
        return res;
    }

    int count = 0;

    public int countSubstrings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        for (int i = 0; i < s.length(); i++) {
            extendPalindrome(s, i, i);
            extendPalindrome(s, i, i + 1);
        }
        return count;
    }

    private void extendPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left--) == s.charAt(right++)) {
            count++;
        }
    }

    public static int countSubstrings3(String s) {
        int length = s.length();
        boolean[] dp = new boolean[length];
        int res = 0;
        for (int j = 0; j < length; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1])) {
                    dp[i] = true;
                    res++;
                } else {
                    dp[i] = false;
                }
            }
        }
        return res;
    }

    public static int countSubstrings4(String s) {
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        int res = 0;
        for (int j = 0; j < length; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) != s.charAt(j)) {
                    continue;
                }
                dp[i][j] = j - i <= 2 || dp[i + 1][j - 1];
                if (dp[i][j]) {
                    res++;
                }
            }
        }
        return res;
    }

    public static int countSubstrings(String s) {
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        int res = 0;
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i; j < length; j++) {
                if (s.charAt(i) != s.charAt(j)) {
                    continue;
                }
                dp[i][j] = j - i <= 2 || dp[i + 1][j - 1];
                if (dp[i][j]) {
                    res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(countSubstrings("abc"));
        System.out.println(countSubstrings("aaa"));
        System.out.println(countSubstrings1("abc"));
        System.out.println(countSubstrings1("aaa"));

        System.out.println(countSubstrings3("abc"));
        System.out.println(countSubstrings3("aaa"));
        System.out.println(countSubstrings4("abc"));
        System.out.println(countSubstrings4("aaa"));
    }


}
