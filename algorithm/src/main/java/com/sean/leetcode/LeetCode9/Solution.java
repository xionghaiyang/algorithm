package com.sean.leetcode.LeetCode9;

/**
 * @Author xionghaiyang
 * @Date 2025-07-22 11:01
 * @Description https://leetcode.cn/problems/palindrome-number
 * 9. 回文数
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 例如，121 是回文，而 123 不是。
 * -2^31 <= x <= 2^31 - 1
 */
public class Solution {

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        char[] str = Integer.toString(x).toCharArray();
        for (int left = 0, right = str.length - 1; left <= right; left++, right--) {
            if (str[left] != str[right]) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome1(int x) {
        if (x < 0 || (x > 0 && x % 10 == 0)) {
            return false;
        }
        int rev = 0;
        while (rev < x / 10) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return rev == x || rev == x / 10;
    }

}
