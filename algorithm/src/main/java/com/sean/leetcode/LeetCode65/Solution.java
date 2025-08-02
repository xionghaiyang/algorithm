package com.sean.leetcode.LeetCode65;

/**
 * @Author xionghaiyang
 * @Date 2025-08-02 16:52
 * @Description https://leetcode.cn/problems/valid-number
 * 65. 有效数字
 * 给定一个字符串 s ，返回 s 是否是一个 有效数字。
 * 例如，下面的都是有效数字："2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"，而接下来的不是："abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"。
 * 一般的，一个 有效数字 可以用以下的规则之一定义：
 * 一个 整数 后面跟着一个 可选指数。
 * 一个 十进制数 后面跟着一个 可选指数。
 * 一个 整数 定义为一个 可选符号 '-' 或 '+' 后面跟着 数字。
 * 一个 十进制数 定义为一个 可选符号 '-' 或 '+' 后面跟着下述规则：
 * 数字 后跟着一个 小数点 .。
 * 数字 后跟着一个 小数点 . 再跟着 数位。
 * 一个 小数点 . 后跟着 数位。
 * 指数 定义为指数符号 'e' 或 'E'，后面跟着一个 整数。
 * 数字 定义为一个或多个数位。
 * 1 <= s.length <= 20
 * s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。
 */
public class Solution {

    public boolean isNumber(String s) {
        int[][] transfer = new int[][]{{0, 2, 7, 1, -1},
                {-1, 2, 7, -1, -1},
                {8, 2, 3, -1, 4},
                {8, 3, -1, -1, 4},
                {-1, 6, -1, 5, -1},
                {-1, 6, -1, -1, -1},
                {8, 6, -1, -1, -1},
                {-1, 3, -1, -1, -1},
                {8, -1, -1, -1, -1}};
        int state = 0;
        for (char c : s.toCharArray()) {
            int index = getIndex(c);
            if (index == -1) {
                return false;
            }
            state = transfer[state][index];
            if (state == -1) {
                return false;
            }
        }
        return state == 2 || state == 3 || state == 6;
    }

    private int getIndex(char c) {
        if (c == ' ') {
            return 0;
        } else if (c >= '0' && c <= '9') {
            return 1;
        } else if (c == '.') {
            return 2;
        } else if (c == '+' || c == '-') {
            return 3;
        } else if (c == 'e' || c == 'E') {
            return 4;
        } else {
            return -1;
        }
    }

}
