package com.sean.leetcode.LeetCode2129;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-11 16:17
 * @Description: https://leetcode.cn/problems/capitalize-the-title/
 * 2129. 将标题首字母大写
 * 给你一个字符串 title ，它由单个空格连接一个或多个单词组成，每个单词都只包含英文字母。
 * 请你按以下规则将每个单词的首字母 大写 ：
 * 如果单词的长度为 1 或者 2 ，所有字母变成小写。
 * 否则，将单词首字母大写，剩余字母变成小写。
 * 请你返回 大写后 的 title 。
 */
public class Solution {

    public String capitalizeTitle(String title) {
        StringBuilder res = new StringBuilder(title);
        int n = title.length();
        int left = 0, right = 0;
        while (right < n) {
            while (right < n && res.charAt(right) != ' ') {
                right++;
            }
            //对每个单词按要求处理
            if (right - left > 2) {
                res.setCharAt(left, Character.toUpperCase(res.charAt(left)));
                left++;
            }
            while (left < right) {
                res.setCharAt(left, Character.toLowerCase(res.charAt(left)));
                left++;
            }
            left = right + 1;
            right++;
        }
        return res.toString();
    }

}
