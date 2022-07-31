package com.sean.leetcode;

/**
 * 判断子序列
 * https://leetcode-cn.com/problems/is-subsequence/
 */
public class LeetCode392 {

    public static boolean isSubsequence(String s, String t) {
        int tIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            while (tIndex < t.length() && ch != t.charAt(tIndex)) {
                tIndex++;
            }
            if (tIndex == t.length()) {
                return false;
            }
            if (ch == t.charAt(tIndex)) {
                tIndex++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isSubsequence("axc", "ahbgdc"));
    }

}
