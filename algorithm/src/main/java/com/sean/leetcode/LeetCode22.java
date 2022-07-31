package com.sean.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode22 {

    List<String> res = new ArrayList<>();
    StringBuilder stringBuilder = new StringBuilder();
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
            res.add(stringBuilder.toString());
            return;
        }
        if (left < n) {
            stringBuilder.append('(');
            dfs(left + 1, right);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        if (left > right && right < n) {
            stringBuilder.append(')');
            dfs(left, right + 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }

}
