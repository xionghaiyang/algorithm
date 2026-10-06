package com.sean.leetcode.LeetCode921;

/**
 * @Author: xionghaiyang
 * @Date: 2022-10-04 23:33
 * @Description: https://leetcode.cn/problems/minimum-add-to-make-parentheses-valid
 * 921. 使括号有效的最少添加
 * 只有满足下面几点之一，括号字符串才是有效的：
 * 它是一个空字符串，或者
 * 它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者
 * 它可以被写作 (A)，其中 A 是有效字符串。
 * 给定一个括号字符串 s ，移动N次，你就可以在字符串的任何位置插入一个括号。
 * 例如，如果 s = "()))" ，你可以插入一个开始括号为 "(()))" 或结束括号为 "())))" 。
 * 返回 为使结果字符串 s 有效而必须添加的最少括号数。
 * 1 <= s.length <= 1000
 * s 只包含 '(' 和 ')' 字符。
 */
public class Solution {

    public int minAddToMakeValid(String s) {
        int res = 0, left = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } else {
                if (left > 0) {
                    left--;
                } else {
                    res++;
                }
            }
        }
        res += left;
        return res;
    }

}
