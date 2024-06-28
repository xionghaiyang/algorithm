package com.sean.leetcode.LeetCode2744;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-17 13:07
 * @Description: https://leetcode.cn/problems/find-maximum-number-of-string-pairs/
 * 2744. 最大字符串配对数目
 * 给你一个下标从 0 开始的数组 words ，数组中包含 互不相同 的字符串。
 * 如果字符串 words[i] 与字符串 words[j] 满足以下条件，我们称它们可以匹配：
 * 字符串 words[i] 等于 words[j] 的反转字符串。
 * 0 <= i < j < words.length
 * 请你返回数组 words 中的 最大 匹配数目。
 * 注意，每个字符串最多匹配一次。
 */
public class Solution {

    public int maximumNumberOfStringPairs(String[] words) {
        int res = 0;
        boolean[][] seen = new boolean[26][26];
        for (String word : words) {
            int x = word.charAt(0) - 'a';
            int y = word.charAt(1) - 'a';
            if (seen[y][x]) {
                res++;
            } else {
                seen[x][y] = true;
            }
        }
        return res;
    }

}
