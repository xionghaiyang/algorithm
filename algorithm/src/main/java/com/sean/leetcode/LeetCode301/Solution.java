package com.sean.leetcode.LeetCode301;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-08-13 12:20
 * @Description https://leetcode.cn/problems/remove-invalid-parentheses
 * 301. 删除无效的括号
 * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
 * 返回所有可能的结果。
 * 答案可以按 任意顺序 返回。
 * 1 <= s.length <= 25
 * s 由小写英文字母以及括号 '(' 和 ')' 组成
 * s 中至多含 20 个括号
 */
public class Solution {

    private List<String> res = new ArrayList<>();

    public List<String> removeInvalidParentheses(String s) {
        int n = s.length();
        //应该删除left个左括号和right个右括号
        int left = 0, right = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left == 0) {
                    right++;
                } else {
                    left--;
                }
            }
        }
        process(s, 0, left, right);
        return res;
    }

    private void process(String s, int start, int left, int right) {
        if (left == 0 && right == 0) {
            if (isValid(s)) {
                res.add(s);
            }
            return;
        }
        int n = s.length();
        for (int i = start; i < n; i++) {
            if (i != start && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }
            if (left + right > n - i) {
                return;
            }
            if (left > 0 && s.charAt(i) == '(') {
                process(s.substring(0, i) + s.substring(i + 1), i, left - 1, right);
            }
            if (right > 0 && s.charAt(i) == ')') {
                process(s.substring(0, i) + s.substring(i + 1), i, left, right - 1);
            }
        }
    }

    private boolean isValid(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                cnt++;
            } else if (s.charAt(i) == ')') {
                cnt--;
                if (cnt < 0) {
                    return false;
                }
            }
        }
        return cnt == 0;
    }

}
