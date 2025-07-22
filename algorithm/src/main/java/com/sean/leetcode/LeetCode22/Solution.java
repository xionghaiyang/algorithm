package com.sean.leetcode.LeetCode22;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-09 20:56
 * @Description: https://leetcode.cn/problems/generate-parentheses
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 1 <= n <= 8
 */
public class Solution {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        process(0, 0, n, new char[n * 2], res);
        return res;
    }

    private void process(int left, int right, int n, char[] str, List<String> res) {
        if (right == n) {
            res.add(String.valueOf(str));
            return;
        }
        if (left < n) {
            str[left + right] = '(';
            process(left + 1, right, n, str, res);
        }
        if (left > right) {
            str[left + right] = ')';
            process(left, right + 1, n, str, res);
        }
    }

}
