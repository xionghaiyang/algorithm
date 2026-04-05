package com.sean.leetcode.LeetCode282;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2026-04-05 18:47
 * @Description https://leetcode.cn/problems/expression-add-operators
 * 282. 给表达式添加运算符
 * 给定一个仅包含数字 0-9 的字符串 num 和一个目标值整数 target ，在 num 的数字之间添加 二元 运算符（不是一元）+、- 或 * ，返回 所有 能够得到 target 的表达式。
 * 注意，返回表达式中的操作数 不应该 包含前导零。
 * 注意，一个数字可以包含多个数位。
 * 1 <= num.length <= 10
 * num 仅含数字
 * -2^31 <= target <= 2^31 - 1
 */
public class Solution {

    private List<String> res;
    private String num;
    private int n;
    private int target;

    public List<String> addOperators(String num, int target) {
        res = new ArrayList<>();
        this.num = num;
        n = num.length();
        this.target = target;
        process("", 0, 0, 0);
        return res;
    }

    private void process(String expr, int i, long cur, long mul) {
        if (i == n) {
            if (cur == target) {
                res.add(expr);
            }
            return;
        }
        long val = 0;
        for (int j = i; j < n && (j == i || num.charAt(i) != '0'); j++) {
            val = val * 10 + (num.charAt(j) - '0');
            if (i == 0) {
                process("" + val, j + 1, val, val);
            } else {
                process(expr + "+" + val, j + 1, cur + val, val);
                process(expr + "-" + val, j + 1, cur - val, -val);
                process(expr + "*" + val, j + 1, cur - mul + mul * val, mul * val);
            }
        }
    }

}
