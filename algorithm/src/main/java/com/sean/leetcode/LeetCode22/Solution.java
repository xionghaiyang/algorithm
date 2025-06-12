package com.sean.leetcode.LeetCode22;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-09 20:56
 * @Description: https://leetcode.cn/problems/generate-parentheses/
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 1 <= n <= 8
 */
public class Solution {

    List<String> res = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    int n;

    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return res;
        }
        this.n = n;
        process(0, 0);
        return res;
    }

    private void process(int left, int right) {
        if (left == n && right == n) {
            res.add(sb.toString());
            return;
        }
        if (left < n) {
            sb.append('(');
            process(left + 1, right);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (left > right) {
            sb.append(')');
            process(left, right + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}
