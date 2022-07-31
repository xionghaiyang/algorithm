package com.sean.leetcode;

/**
 * 验证回文串
 * https://leetcode-cn.com/problems/valid-palindrome/
 */
public class LeetCode125 {

    public static boolean isPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return true;
        }
        char[] ch = s.toCharArray();
        int left = 0, right = ch.length - 1;
        char a = 'a', b = 'b';
        while (left < right) {
            a = ch[left];
            if (!((a >= '0' && a <= '9') || (a >= 'a' && a <= 'z') || (a >= 'A' && a <= 'Z'))) {
                left++;
                continue;
            }
            b = ch[right];
            if (!((b >= '0' && b <= '9') || (b >= 'a' && b <= 'z') || (b >= 'A' && b <= 'Z'))) {
                right--;
                continue;
            }
            if (a >= 'A' && a <= 'Z') {
                a += 32;
            }
            if (b >= 'A' && b <= 'Z') {
                b += 32;
            }
            if (a != b) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));
    }
}
