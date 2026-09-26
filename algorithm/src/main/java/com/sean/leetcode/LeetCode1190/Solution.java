package com.sean.leetcode.LeetCode1190;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-27 05:58
 * @Description: https://leetcode.cn/problems/reverse-substrings-between-each-pair-of-parentheses
 * 1190. 反转每对括号间的子串
 * 给出一个字符串 s（仅含有小写英文字母和括号）。
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 * 注意，您的结果中 不应 包含任何括号。
 * 1 <= s.length <= 2000
 * s 中只有小写英文字母和括号
 * 题目测试用例确保所有括号都是成对出现的
 */
public class Solution {

    private int i = 0;

    public String reverseParentheses(String s) {
        return process(s.toCharArray()).toString();
    }

    private StringBuilder process(char[] str) {
        StringBuilder res = new StringBuilder();
        while (i < str.length) {
            char c = str[i++];
            if (c == ')') {
                return res.reverse();
            }
            if (c == '(') {
                res.append(process(str));
            } else {
                res.append(c);
            }
        }
        return res;
    }

    public String reverseParentheses1(String s) {
        char[] str = s.toCharArray();
        int n = s.length();
        int[] links = new int[n];
        int[] stack = new int[n];
        int top = -1;
        for (int i = 0; i < n; i++) {
            char c = str[i];
            if (c == '(') {
                stack[++top] = i;
            } else if (c == ')') {
                int j = stack[top--];
                links[i] = j;
                links[j] = i;
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0, step = 1; i < n; i += step) {
            char c = str[i];
            if (c == '(' || c == ')') {
                i = links[i];
                step *= -1;
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

}
