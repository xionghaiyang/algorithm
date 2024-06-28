package com.sean.leetcode.LeetCode22;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-09 20:56
 * @Description: https://leetcode.cn/problems/generate-parentheses/
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
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
        dfs(0, 0);
        return res;
    }

    private void dfs(int left, int right) {
        if (left == n && right == n) {
            res.add(sb.toString());
            return;
        }
        if (left < n) {
            sb.append('(');
            dfs(left + 1, right);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (left > right && right < n) {
            sb.append(')');
            dfs(left, right + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}
