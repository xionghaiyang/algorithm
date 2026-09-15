package com.sean.leetcode.LeetCode434;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-15 09:52
 * @Description: https://leetcode.cn/problems/number-of-segments-in-a-string
 * 434. 字符串中的单词数
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 */
public class Solution {

    public int countSegments(String s) {
        int n = s.length();
        int res = 0;
        for (int i = 0; i < n; i++) {
            if ((i == 0 || s.charAt(i - 1) == ' ') && s.charAt(i) != ' ') {
                res++;
            }
        }
        return res;
    }

}
