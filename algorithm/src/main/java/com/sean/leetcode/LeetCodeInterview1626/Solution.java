package com.sean.leetcode.LeetCodeInterview1626;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author xionghaiyang
 * @Date 2026-03-24 17:39
 * @Description https://leetcode.cn/problems/calculator-lcci
 * 面试题 16.26. 计算器
 * 给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。
 * 表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。
 * 整数除法仅保留整数部分。
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 */
public class Solution {

    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        char preSign = '+';
        int n = s.length();
        int num = 0;
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + (s.charAt(i) - '0');
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    default:
                        stack.push(stack.pop() / num);
                        break;
                }
                preSign = s.charAt(i);
                num = 0;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    //如果有括号
    public int calculate1(String s) {
        s = s.replaceAll(" ", "");
        return f(s.toCharArray(), 0)[0];
    }

    private int[] f(char[] str, int i) {
        LinkedList<String> list = new LinkedList<>();
        int cur = 0;
        int[] next = null;
        while (i < str.length && str[i] != ')') {
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + (str[i++] - '0');
            } else if (str[i] != '(') {//遇到的是运算符号
                addNum(list, cur);
                list.addLast(String.valueOf(str[i++]));
                cur = 0;
            } else {
                next = f(str, i + 1);
                cur = next[0];
                i = next[1] + 1;
            }
        }
        addNum(list, cur);
        return new int[]{getNum(list), i};
    }

    private void addNum(LinkedList<String> list, int num) {
        if (!list.isEmpty()) {
            int cur = 0;
            String top = list.pollLast();
            if ("+".equals(top) || "-".equals(top)) {
                list.addLast(top);
            } else {
                cur = Integer.valueOf(list.pollLast());
                num = "*".equals(top) ? cur * num : cur / num;
            }
        }
        list.addLast(String.valueOf(num));
    }

    private int getNum(LinkedList<String> list) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while (!list.isEmpty()) {
            cur = list.pollFirst();
            if ("+".equals(cur)) {
                add = true;
            } else if ("-".equals(cur)) {
                add = false;
            } else {
                num = Integer.valueOf(cur);
                res += add ? num : -num;
            }
        }
        return res;
    }

}
