package com.sean.leetcode.LeetCode1967;

/**
 * @Author: xionghaiyang
 * @Date: 2026-06-29 07:29
 * @Description: https://leetcode.cn/problems/number-of-strings-that-appear-as-substrings-in-word
 * 1967. 作为子字符串出现在单词中的字符串数目
 * 给你一个字符串数组 patterns 和一个字符串 word ，统计 patterns 中有多少个字符串是 word 的子字符串。
 * 返回字符串数目。
 * 子字符串 是字符串中的一个连续字符序列。
 * 1 <= patterns.length <= 100
 * 1 <= patterns[i].length <= 100
 * 1 <= word.length <= 100
 * patterns[i] 和 word 由小写英文字母组成
 */
public class Solution {

    public int numOfStrings(String[] patterns, String word) {
        int res = 0;
        for (String pattern : patterns) {
            if (check(pattern, word)) {
                res++;
            }
        }
        return res;
    }

    private boolean check(String pattern, String word) {
        int m = pattern.length(), n = word.length();
        for (int i = 0; i + m <= n; i++) {
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if (word.charAt(i + j) != pattern.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

}
