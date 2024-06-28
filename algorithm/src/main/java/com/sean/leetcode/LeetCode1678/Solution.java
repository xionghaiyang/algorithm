package com.sean.leetcode.LeetCode1678;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-06 23:28
 * @Description: https://leetcode.cn/problems/goal-parser-interpretation/
 * 1678. 设计 Goal 解析器
 * 请你设计一个可以解释字符串 command 的 Goal 解析器 。
 * command 由 "G"、"()" 和/或 "(al)" 按某种顺序组成。
 * Goal 解析器会将 "G" 解释为字符串 "G"、"()" 解释为字符串 "o" ，"(al)" 解释为字符串 "al" 。
 * 然后，按原顺序将经解释得到的字符串连接成一个字符串。
 * 给你字符串 command ，返回 Goal 解析器 对 command 的解释结果。
 */
public class Solution {

    public String interpret(String command) {
        if (command == null || command.length() == 0) {
            return "";
        }
        int n = command.length();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (command.charAt(i) == 'G') {
                stringBuilder.append("G");
            } else if (command.charAt(i) == '(') {
                if (command.charAt(i + 1) == ')') {
                    stringBuilder.append("o");
                } else {
                    stringBuilder.append("al");
                }
            }
        }
        return stringBuilder.toString();
    }

}
