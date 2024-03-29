package com.sean.leetcode.LeetCode1758;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-29 09:30
 * @Description: https://leetcode.cn/problems/minimum-changes-to-make-alternating-binary-string/
 * 1758. 生成交替二进制字符串的最少操作数
 * 给你一个仅由字符 '0' 和 '1' 组成的字符串 s 。
 * 一步操作中，你可以将任一 '0' 变成 '1' ，或者将 '1' 变成 '0' 。
 * 交替字符串 定义为：如果字符串中不存在相邻两个字符相等的情况，那么该字符串就是交替字符串。
 * 例如，字符串 "010" 是交替字符串，而字符串 "0100" 不是。
 * 返回使 s 变成 交替字符串 所需的 最少 操作数。
 */
public class Solution {

    public int minOperations1(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        return process(str, 0);
    }

    private int process(char[] str, int i) {
        if (i == str.length - 1) {
            return str[i - 1] != str[i] ? 0 : 1;
        }
        if (i == 0) {
            int res1 = process(str, i + 1);
            str[i] = str[i] == '0' ? '1' : '0';
            int res2 = 1 + process(str, i + 1);
            str[i] = str[i] == '0' ? '1' : '0';
            return Math.min(res1, res2);
        }
        if (str[i] == str[i - 1]) {
            str[i] = str[i] == '0' ? '1' : '0';
            int res = 1 + process(str, i + 1);
            str[i] = str[i] == '0' ? '1' : '0';
            return res;
        } else {
            return process(str, i + 1);
        }
    }

    public int minOperations(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != (char) ('0' + i % 2)) {
                count++;
            }
        }
        return Math.min(count, s.length() - count);
    }

}
