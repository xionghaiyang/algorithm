package com.sean.leetcode;

/**
 * 回文数
 * https://leetcode-cn.com/problems/palindrome-number/
 */
public class LeetCode9 {

    public static boolean isPalindrome(int x) {
        if (x == 0) {
            return true;
        }
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        int reverse = 0;
        while (x > reverse) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }
        return x == reverse || x == reverse / 10;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(123));
    }
}
