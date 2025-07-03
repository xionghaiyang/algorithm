package com.sean.leetcode.LeetCode125;

/**
 * @Author xionghaiyang
 * @Date 2025-07-03 18:18
 * @Description https://leetcode.cn/problems/valid-palindrome
 * 125. 验证回文串
 * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。
 * 则可以认为该短语是一个 回文串 。
 * 字母和数字都属于字母数字字符。
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 * 1 <= s.length <= 2 * 10^5
 * s 仅由可打印的 ASCII 字符组成
 */
public class Solution {

    public boolean isPalindrome(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            if (!('0' <= s.charAt(left) && s.charAt(left) <= '9') && !('a' <= s.charAt(left) && s.charAt(left) <= 'z') && !('A' <= s.charAt(left) && s.charAt(left) <= 'Z')) {
                left++;
            } else if (!('0' <= s.charAt(right) && s.charAt(right) <= '9') && !('a' <= s.charAt(right) && s.charAt(right) <= 'z') && !('A' <= s.charAt(right) && s.charAt(right) <= 'Z')) {
                right--;
            } else if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }

}
