package com.sean.leetcode.LeetCode1021;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-08 06:38
 * @Description: https://leetcode.cn/problems/remove-outermost-parentheses
 * 1021. 删除最外层的括号
 * 有效括号字符串为空 ""、"(" + A + ")" 或 A + B ，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。
 * 例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。
 * 如果有效字符串 s 非空，且不存在将其拆分为 s = A + B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。
 * 给出一个非空有效字符串 s，考虑将其进行原语化分解，使得：s = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。
 * 对 s 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 s 。
 * 1 <= s.length <= 10^5
 * s[i] 为 '(' 或 ')'
 * s 是一个有效括号字符串
 */
public class Solution {

    public String removeOuterParentheses(String s) {
        char[] str = s.toCharArray();
        int size = 0, depth = 0;
        for (char c : str) {
            if (c == '(') {
                if (depth > 0) {
                    str[size++] = c;
                }
                depth++;
            } else {
                depth--;
                if (depth > 0) {
                    str[size++] = c;
                }
            }
        }
        return new String(str, 0, size);
    }

}
