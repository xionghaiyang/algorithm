package com.sean.leetcode.LeetCode1930;

/**
 * @Author xionghaiyang
 * @Date 2025-11-21 12:01
 * @Description https://leetcode.cn/problems/unique-length-3-palindromic-subsequences
 * 1930. 长度为 3 的不同回文子序列
 * 给你一个字符串 s ，返回 s 中 长度为 3 的不同回文子序列 的个数。
 * 即便存在多种方法来构建相同的子序列，但相同的子序列只计数一次。
 * 回文 是正着读和反着读一样的字符串。
 * 子序列 是由原字符串删除其中部分字符（也可以不删除）且不改变剩余字符之间相对顺序形成的一个新字符串。
 * 例如，"ace" 是 "abcde" 的一个子序列。
 * 3 <= s.length <= 10^5
 * s 仅由小写英文字母组成
 */
public class Solution {

    public int countPalindromicSubsequence(String s) {
        int res = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            int i = s.indexOf(c);
            if (i < 0) {
                continue;
            }
            int j = s.lastIndexOf(c);
            boolean[] has = new boolean[26];
            for (int k = i + 1; k < j; k++) {
                int mid = s.charAt(k) - 'a';
                if (!has[mid]) {
                    has[mid] = true;
                    res++;
                }
            }
        }
        return res;
    }

}
