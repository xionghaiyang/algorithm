package com.sean.leetcode.LeetCode856;

/**
 * @Author: xionghaiyang
 * @Date: 2022-10-09 08:26
 * @Description: https://leetcode.cn/problems/score-of-parentheses
 * 856. 括号的分数
 * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
 * () 得 1 分。
 * AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
 * (A) 得 2 * A 分，其中 A 是平衡括号字符串。
 * S 是平衡括号字符串，且只含有 ( 和 ) 。
 * 2 <= S.length <= 50
 */
public class Solution {

    public int scoreOfParentheses(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        int res = 0;
        for (int i = 0, depth = 0; i < n; i++) {
            if (str[i] == '(') {
                depth++;
            } else {
                depth--;
                if (str[i - 1] == '(') {
                    res += 1 << depth;
                }
            }
        }
        return res;
    }

}
