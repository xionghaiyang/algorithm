package com.sean.leetcode.LeetCode1614;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-28 06:23
 * @Description: https://leetcode.cn/problems/maximum-nesting-depth-of-the-parentheses
 * 1614. 括号的最大嵌套深度
 * 给定 有效括号字符串 s，返回 s 的 嵌套深度。
 * 嵌套深度是嵌套括号的 最大 数量。
 * 1 <= s.length <= 100
 * s 由数字 0-9 和字符 '+'、'-'、'*'、'/'、'('、')' 组成
 * 题目数据保证括号字符串 s 是 有效的括号字符串
 */
public class Solution {

    public int maxDepth(String s) {
        int n = s.length();
        int res = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                res = Math.max(res, ++cnt);
            } else if (c == ')') {
                cnt--;
            }
        }
        return res;
    }

}
