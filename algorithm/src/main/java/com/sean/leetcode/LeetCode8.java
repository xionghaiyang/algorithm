package com.sean.leetcode;

/**
 * 字符串转换整数 (atoi)
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 */
public class LeetCode8 {

    public static int myAtoi(String s) {
        s = s.trim();
        if (s.length() == 0) {
            return 0;
        }
        if (!Character.isDigit(s.charAt(0)) && s.charAt(0) != '-' && s.charAt(0) != '+') {
            return 0;
        }
        int res = 0;
        boolean flag = s.charAt(0) == '-';
        int i = !Character.isDigit(s.charAt(0)) ? 1 : 0;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            int temp = ((flag ? Integer.MIN_VALUE : Integer.MIN_VALUE + 1) + (s.charAt(i) - '0')) / 10;
            if (temp > res) {
                return flag ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            res = res * 10 - (s.charAt(i++) - '0');
        }
        return flag ? res : -res;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("42"));
        System.out.println(myAtoi("   -42"));
        System.out.println(myAtoi("4193 with words"));
        System.out.println(myAtoi("words and 987"));
        System.out.println(myAtoi("-91283472332"));
    }
}
