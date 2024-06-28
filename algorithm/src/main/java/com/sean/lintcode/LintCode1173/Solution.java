package com.sean.lintcode.LintCode1173;

/**
 * @Author xionghaiyang
 * @Date 2022-08-06 09:20
 * @Description https://www.lintcode.com/problem/1173/?showListFe=true&page=1&pageSize=50
 * 1173 反转字符串 III
 * 给定一个字符串句子，反转句子中每一个单词的所有字母，同时保持空格和最初的单词顺序。
 */
public class Solution {

    public String reverseWords(String s) {
        StringBuilder res = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        int index = 0;
        int n = s.length();
        while (index < n) {
            int start = index;
            while (index < n && s.charAt(index) != ' ') {
                temp.append(s.charAt(index));
                index++;
            }
            res.append(temp.reverse().toString());
            temp.setLength(0);
            while (index < n && s.charAt(index) == ' ') {
                index++;
                res.append(' ');
            }
        }
        return res.toString();
    }

}
