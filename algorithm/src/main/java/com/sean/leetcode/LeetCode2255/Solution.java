package com.sean.leetcode.LeetCode2255;

/**
 * @Author xionghaiyang
 * @Date 2025-03-24 08:08
 * @Description https://leetcode.cn/problems/count-prefixes-of-a-given-string
 * 2255. 统计是给定字符串前缀的字符串数目
 * 给你一个字符串数组 words 和一个字符串 s ，其中 words[i] 和 s 只包含 小写英文字母 。
 * 请你返回 words 中是字符串 s 前缀 的 字符串数目 。
 * 一个字符串的 前缀 是出现在字符串开头的子字符串。
 * 子字符串 是一个字符串中的连续一段字符序列。
 * 1 <= words.length <= 1000
 * 1 <= words[i].length, s.length <= 10
 * words[i] 和 s 只 包含小写英文字母。
 */
public class Solution {

    public int countPrefixes(String[] words, String s) {
        int res = 0;
        for (String word : words) {
            if (s.startsWith(word)) {
                res++;
            }
        }
        return res;
    }

}
