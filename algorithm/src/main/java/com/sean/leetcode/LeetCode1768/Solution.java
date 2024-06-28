package com.sean.leetcode.LeetCode1768;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-23 20:12
 * @Description: https://leetcode.cn/problems/merge-strings-alternately/
 * 1768. 交替合并字符串
 * 给你两个字符串 word1 和 word2 。
 * 请你从 word1 开始，通过交替添加字母来合并字符串。
 * 如果一个字符串比另一个字符串长，就将多出来的字母追加到合并后字符串的末尾。
 * 返回 合并后的字符串 。
 */
public class Solution {

    public String mergeAlternately(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        while (index < m || index < n) {
            if (index < m && index < n) {
                stringBuilder.append(word1.charAt(index)).append(word2.charAt(index));
            } else if (index < m) {
                stringBuilder.append(word1.substring(index));
                break;
            } else {
                stringBuilder.append(word2.substring(index));
                break;
            }
            index++;
        }
        return stringBuilder.toString();
    }

}
