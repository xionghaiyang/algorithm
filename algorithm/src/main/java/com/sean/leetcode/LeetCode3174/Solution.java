package com.sean.leetcode.LeetCode3174;

/**
 * @Author xionghaiyang
 * @Date 2024-09-05 08:38
 * @Description https://leetcode.cn/problems/clear-digits/
 * 3174. 清除数字
 * 给你一个字符串 s 。
 * 你的任务是重复以下操作删除 所有 数字字符：
 * 删除 第一个数字字符 以及它左边 最近 的 非数字 字符。
 * 请你返回删除所有数字字符以后剩下的字符串。
 */
public class Solution {

    public String clearDigits(String s) {
        StringBuilder res = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                res.deleteCharAt(res.length() - 1);
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

}
