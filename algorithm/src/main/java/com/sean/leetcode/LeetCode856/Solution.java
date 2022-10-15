package com.sean.leetcode.LeetCode856;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-09 08:26
 * @Description: https://leetcode.cn/problems/score-of-parentheses/
 * 856. 括号的分数
 * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
 * () 得 1 分。
 * AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
 * (A) 得 2 * A 分，其中 A 是平衡括号字符串。
 */
public class Solution {

    public int scoreOfParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(0);
            } else {
                int v = stack.pop();
                stack.push(stack.pop() + Math.max(2 * v, 1));
            }
        }
        return stack.peek();
    }

}
