package com.sean.leetcode.LeetCode2185;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-09 10:14
 * @Description: https://leetcode.cn/problems/counting-words-with-a-given-prefix/
 * 2185. 统计包含给定前缀的字符串
 * 给你一个字符串数组 words 和一个字符串 pref 。
 * 返回 words 中以 pref 作为 前缀 的字符串的数目。
 * 字符串 s 的 前缀 就是  s 的任一前导连续字符串。
 */
public class Solution {

    public int prefixCount(String[] words, String pref) {
        int res = 0;
        for (String word : words) {
            if (word.startsWith(pref)) {
                res++;
            }
        }
        return res;
    }

}
