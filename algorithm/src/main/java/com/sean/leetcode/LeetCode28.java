package com.sean.leetcode;

public class LeetCode28 {

    public static int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || needle.length() == 0) {
            return 0;
        }
        int m = haystack.length();
        int n = needle.length();
        for (int i = 0; i < m - n + 1; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(strStr("hello", "ll"));
        System.out.println(strStr("aaaaa", "bba"));
        System.out.println(strStr("", ""));
    }
}
