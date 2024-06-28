package com.sean.leetcode.LeetCode241;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-25 17:28
 * @Description: https://leetcode.cn/problems/different-ways-to-add-parentheses/description/
 * 241. 为运算表达式设计优先级
 * 给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。
 * 你可以 按任意顺序 返回答案。
 * 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 104 。
 */
public class Solution {

    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> ops = new ArrayList<>();
        for (int i = 0; i < expression.length(); ) {
            if (!Character.isDigit(expression.charAt(i))) {
                if (expression.charAt(i) == '+') {
                    ops.add(-1);
                } else if (expression.charAt(i) == '-') {
                    ops.add(-2);
                } else {
                    ops.add(-3);
                }
                i++;
            } else {
                int t = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    t = t * 10 + expression.charAt(i) - '0';
                    i++;
                }
                ops.add(t);
            }
        }
        List<Integer>[][] dp = new List[ops.size()][ops.size()];
        for (int i = 0; i < ops.size(); i++) {
            for (int j = 0; j < ops.size(); j++) {
                dp[i][j] = new ArrayList<>();
            }
        }
        return dfs(dp, 0, ops.size() - 1, ops);
    }

    private List<Integer> dfs(List<Integer>[][] dp, int l, int r, List<Integer> ops) {
        if (dp[l][r].isEmpty()) {
            if (l == r) {
                dp[l][r].add(ops.get(l));
            } else {
                for (int i = l; i < r; i += 2) {
                    List<Integer> left = dfs(dp, l, i, ops);
                    List<Integer> right = dfs(dp, i + 2, r, ops);
                    for (int lv : left) {
                        for (int rv : right) {
                            if (ops.get(i + 1) == -1) {
                                dp[l][r].add(lv + rv);
                            } else if (ops.get(i + 1) == -2) {
                                dp[l][r].add(lv - rv);
                            } else {
                                dp[l][r].add(lv * rv);
                            }
                        }
                    }
                }
            }
        }
        return dp[l][r];
    }

}
