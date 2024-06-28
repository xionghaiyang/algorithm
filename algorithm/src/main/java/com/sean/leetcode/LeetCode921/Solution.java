package com.sean.leetcode.LeetCode921;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-04 23:33
 * @Description: https://leetcode.cn/problems/minimum-add-to-make-parentheses-valid/
 * 921. 使括号有效的最少添加
 * 只有满足下面几点之一，括号字符串才是有效的：
 * 它是一个空字符串，或者
 * 它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者
 * 它可以被写作 (A)，其中 A 是有效字符串。
 * 给定一个括号字符串 s ，移动N次，你就可以在字符串的任何位置插入一个括号。
 * 例如，如果 s = "()))" ，你可以插入一个开始括号为 "(()))" 或结束括号为 "())))" 。
 * 返回 为使结果字符串 s 有效而必须添加的最少括号数。
 */
public class Solution {


    public int minAddToMakeValid(String s) {
        int n = s.length();
        int leftCount = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftCount++;
            } else {
                if (leftCount > 0) {
                    leftCount--;
                } else {
                    res++;
                }
            }
        }
        res += leftCount;
        return res;
    }
}
