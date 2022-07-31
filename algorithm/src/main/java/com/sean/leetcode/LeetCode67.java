package com.sean.leetcode;

public class LeetCode67 {

    public static String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int lengthA = a.length();
        int lengthB = b.length();
        int n = Math.max(lengthA, lengthB), carry = 0;
        for (int i = 0; i < n; i++) {
            carry += i < lengthA ? (a.charAt(lengthA - 1 - i) - '0') : 0;
            carry += i < lengthB ? (b.charAt(lengthB - 1 - i) - '0') : 0;
            res.append((char) (carry % 2 + '0'));
            carry /= 2;
        }
        if (carry > 0) {
            res.append('1');
        }
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(addBinary("11", "1"));
    }
}
