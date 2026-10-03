package com.sean.leetcode.LeetCode678;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-04 06:13
 * @Description: https://leetcode.cn/problems/valid-parenthesis-string
 * 678. 有效的括号字符串
 * 给你一个只包含三种字符的字符串，支持的字符类型分别是 '('、')' 和 '*'。
 * 请你检验这个字符串是否为有效字符串，如果是 有效 字符串返回 true 。
 * 有效 字符串符合如下规则：
 * 任何左括号 '(' 必须有相应的右括号 ')'。
 * 任何右括号 ')' 必须有相应的左括号 '(' 。
 * 左括号 '(' 必须在对应的右括号之前 ')'。
 * '*' 可以被视为单个右括号 ')' ，或单个左括号 '(' ，或一个空字符串 ""。
 * 1 <= s.length <= 100
 * s[i] 为 '('、')' 或 '*'
 */
public class Solution {

    public boolean checkValidString(String s) {
        Deque<Integer> leftStack = new ArrayDeque<>();
        Deque<Integer> asteriskStack = new ArrayDeque<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftStack.push(i);
            } else if (c == '*') {
                asteriskStack.push(i);
            } else {
                if (!leftStack.isEmpty()) {
                    leftStack.pop();
                } else if (!asteriskStack.isEmpty()) {
                    asteriskStack.pop();
                } else {
                    return false;
                }
            }
        }
        while (!leftStack.isEmpty() && !asteriskStack.isEmpty()) {
            int leftIndex = leftStack.pop();
            int asteriskIndex = asteriskStack.pop();
            if (leftIndex > asteriskIndex) {
                return false;
            }
        }
        return leftStack.isEmpty();
    }

    public boolean checkValidString1(String s) {
        int n = s.length(), min = 0, max = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                min++;
                max++;
            } else if (c == ')') {
                min = Math.max(min - 1, 0);
                max--;
                if (max < 0) {
                    return false;
                }
            } else {
                min = Math.max(min - 1, 0);
                max++;
            }
        }
        return min == 0;
    }

}
