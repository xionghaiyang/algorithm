package com.sean.leetcode.LeetCode224;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-25 18:39
 * @Description: https://leetcode.cn/problems/basic-calculator/description/
 * 224. 基本计算器
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 */
public class Solution {

    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        int sign = 1;
        int res = 0;
        int n = s.length();
        for (int i = 0; i < n; ) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = stack.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -stack.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                stack.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                stack.pop();
                i++;
            } else {
                int num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                res += sign * num;
            }
        }
        return res;
    }

}
