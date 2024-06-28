package com.sean.leetcode.LeetCode1048;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-27 08:29
 * @Description: https://leetcode.cn/problems/longest-string-chain/
 * 1048. 最长字符串链
 * 给出一个单词数组 words ，其中每个单词都由小写英文字母组成。
 * 如果我们可以 不改变其他字符的顺序 ，在 wordA 的任何地方添加 恰好一个 字母使其变成 wordB ，那么我们认为 wordA 是 wordB 的 前身 。
 * 例如，"abc" 是 "abac" 的 前身 ，而 "cba" 不是 "bcad" 的 前身
 * 词链是单词 [word_1, word_2, ..., word_k] 组成的序列，k >= 1，
 * 其中 word1 是 word2 的前身，word2 是 word3 的前身，依此类推。一个单词通常是 k == 1 的 单词链 。
 * 从给定单词列表 words 中选择单词组成词链，返回 词链的 最长可能长度 。
 */
public class Solution {

    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        Map<String, Integer> map = new HashMap<>();
        int res = 0;
        for (String word : words) {
            int count = 1;
            for (int i = 0; i < word.length(); i++) {
                StringBuilder sb = new StringBuilder(word);
                sb.deleteCharAt(i);
                String str = sb.toString();
                if (map.containsKey(str)) {
                    count = Math.max(count, map.get(str) + 1);
                }
            }
            map.put(word, count);
            res = Math.max(res, count);
        }
        return res;
    }

}
