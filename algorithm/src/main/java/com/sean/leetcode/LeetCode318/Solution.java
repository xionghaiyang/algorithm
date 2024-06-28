package com.sean.leetcode.LeetCode318;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-11-06 09:45
 * @Description: https://leetcode.cn/problems/maximum-product-of-word-lengths/
 * 318. 最大单词长度乘积
 * 给你一个字符串数组 words ，找出并返回 length(words[i]) * length(words[j]) 的最大值，
 * 并且这两个单词不含有公共字母。如果不存在这样的两个单词，返回 0 。
 */
public class Solution {

    public int maxProduct(String[] words) {
        int n = words.length;
        int[] masks = new int[n];
        for (int i = 0; i < n; i++) {
            String word = words[i];
            int m = word.length();
            for (int j = 0; j < m; j++) {
                masks[i] |= 1 << (word.charAt(j) - 'a');
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((masks[i] & masks[j]) == 0) {
                    res = Math.max(res, words[i].length() * words[j].length());
                }
            }
        }
        return res;
    }

}
