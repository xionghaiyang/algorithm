package com.sean.leetcode.LeetCode3110;

/**
 * @Author xionghaiyang
 * @Date 2025-03-15 09:08
 * @Description https://leetcode.cn/problems/score-of-a-string
 * 3110. 字符串的分数
 * 给你一个字符串 s 。
 * 一个字符串的 分数 定义为相邻字符 ASCII 码差值绝对值的和。
 * 请你返回 s 的 分数 。
 * 2 <= s.length <= 100
 * s 只包含小写英文字母。
 */
public class Solution {

    public int scoreOfString(String s) {
        int n = s.length();
        int res = 0;
        for (int i = 1; i < n; i++) {
            res += Math.abs(s.charAt(i) - s.charAt(i - 1));
        }
        return res;
    }

}
