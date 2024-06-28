package com.sean.leetcode.LeetCode1616;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-03-20 08:50
 * @Description: https://leetcode.cn/problems/split-two-strings-to-make-palindrome/
 * 1616. 分割两个字符串得到回文串
 * 给你两个字符串 a 和 b ，它们长度相同。
 * 请你选择一个下标，将两个字符串都在 相同的下标 分割开。
 * 由 a 可以得到两个字符串： aprefix 和 asuffix ，满足 a = aprefix + asuffix ，
 * 同理，由 b 可以得到两个字符串 bprefix 和 bsuffix ，满足 b = bprefix + bsuffix 。
 * 请你判断 aprefix + bsuffix 或者 bprefix + asuffix 能否构成回文串。
 * 当你将一个字符串 s 分割成 sprefix 和 ssuffix 时， ssuffix 或者 sprefix 可以为空。
 * 比方说， s = "abc" 那么 "" + "abc" ， "a" + "bc" ， "ab" + "c" 和 "abc" + "" 都是合法分割。
 * 如果 能构成回文字符串 ，那么请返回 true，否则返回 false 。
 * 注意， x + y 表示连接字符串 x 和 y 。
 */
public class Solution {

    public boolean checkPalindromeFormation(String a, String b) {
        return process(a, b) || process(b, a);
    }

    private boolean process(String a, String b) {
        int n = a.length();
        int left = 0, right = n - 1;
        while (left < right && a.charAt(left) == b.charAt(right)) {
            left++;
            right--;
        }
        if (left >= right) {
            return true;
        }
        return f(a, left, right) || f(b, left, right);
    }

    private boolean f(String a, int left, int right) {
        while (left < right && a.charAt(left) == a.charAt(right)) {
            left++;
            right--;
        }
        return left >= right;
    }

}
