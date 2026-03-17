package com.sean.leetcode.LeetCodeInterview0809;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2026-03-17 19:04
 * @Description https://leetcode.cn/problems/bracket-lcci
 * 面试题 08.09. 括号
 * 括号。
 * 设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。
 * 说明：解集不能包含重复的子集。
 */
public class Solution {

    private List<String> res = new ArrayList<>();
    private int n;
    private char[] str;

    public List<String> generateParenthesis(int n) {
        this.n = n;
        str = new char[n * 2];
        process(0, n, n);
        return res;
    }

    private void process(int i, int left, int right) {
        if (i == n * 2) {
            res.add(String.valueOf(str));
            return;
        }
        if (left > 0) {
            str[i] = '(';
            process(i + 1, left - 1, right);
        }
        if (left < right) {
            str[i] = ')';
            process(i + 1, left, right - 1);
        }
    }

}
