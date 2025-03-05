package com.sean.leetcode.LeetCode1328;

/**
 * @Author xionghaiyang
 * @Date 2025-03-05 07:45
 * @Description https://leetcode.cn/problems/break-a-palindrome/
 * 1328. 破坏回文串
 * 给你一个由小写英文字母组成的回文字符串 palindrome ，请你将其中 一个 字符用任意小写英文字母替换，
 * 使得结果字符串的 字典序最小 ，且 不是 回文串。
 * 请你返回结果字符串。
 * 如果无法做到，则返回一个 空串 。
 * 如果两个字符串长度相同，那么字符串 a 字典序比字符串 b 小可以这样定义：
 * 在 a 和 b 出现不同的第一个位置上，字符串 a 中的字符严格小于 b 中的对应字符。
 * 例如，"abcc” 字典序比 "abcd" 小，因为不同的第一个位置是在第四个字符，显然 'c' 比 'd' 小。
 * 1 <= palindrome.length <= 1000
 * palindrome 只包含小写英文字母。
 */
public class Solution {

    public String breakPalindrome(String palindrome) {
        int n = palindrome.length();
        if (n == 1) {
            return "";
        }
        char[] str = palindrome.toCharArray();
        for (int i = 0; i * 2 + 1 < n; i++) {
            if (str[i] != 'a') {
                str[i] = 'a';
                return new String(str);
            }
        }
        str[n - 1] = 'b';
        return new String(str);
    }

}
