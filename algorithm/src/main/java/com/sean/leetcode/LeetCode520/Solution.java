package com.sean.leetcode.LeetCode520;

/**
 * @Author xionghaiyang
 * @Date 2024-06-23 18:09
 * @Description https://leetcode.cn/problems/detect-capital/
 * 520. 检测大写字母
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 * 全部字母都是大写，比如 "USA" 。
 * 单词中所有字母都不是大写，比如 "leetcode" 。
 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google" 。
 * 给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。
 */
public class Solution {

    public boolean detectCapitalUse(String word) {
        if (word.length() >= 2 && Character.isLowerCase(word.charAt(0)) && Character.isUpperCase(word.charAt(1))) {
            return false;
        }
        for (int i = 2; i < word.length(); i++) {
            if (Character.isLowerCase(word.charAt(i)) ^ Character.isLowerCase(word.charAt(1))) {
                return false;
            }
        }
        return true;
    }

}
