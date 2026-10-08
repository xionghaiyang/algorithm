package com.sean.leetcode.LeetCode1541;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-09 06:11
 * @Description: https://leetcode.cn/problems/minimum-insertions-to-balance-a-parentheses-string
 * 1541. 平衡括号字符串的最少插入次数
 * 给你一个括号字符串 s ，它只包含字符 '(' 和 ')' 。
 * 一个括号字符串被称为平衡的当它满足：
 * 任何左括号 '(' 必须对应两个连续的右括号 '))' 。
 * 左括号 '(' 必须在对应的连续两个右括号 '))' 之前。
 * 比方说 "())"， "())(())))" 和 "(())())))" 都是平衡的， ")()"， "()))" 和 "(()))" 都是不平衡的。
 * 你可以在任意位置插入字符 '(' 和 ')' 使字符串平衡。
 * 请你返回让 s 平衡的最少插入次数。
 * 1 <= s.length <= 10^5
 * s 只包含 '(' 和 ')' 。
 */
public class Solution {

    public int minInsertions(String s) {
        char[] str = s.toCharArray();
        int n = s.length();
        int res = 0, left = 0;
        for (int i = 0; i < n; i++) {
            char c = str[i];
            if (c == '(') {
                left++;
            } else {
                if (left > 0) {
                    left--;
                } else {
                    res++;
                }
                if (i + 1 < n && str[i + 1] == ')') {
                    i++;
                } else {
                    res++;
                }
            }
        }
        res += 2 * left;
        return res;
    }

}
