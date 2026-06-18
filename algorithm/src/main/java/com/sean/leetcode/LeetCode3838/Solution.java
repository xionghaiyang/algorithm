package com.sean.leetcode.LeetCode3838;

/**
 * @Author: xionghaiyang
 * @Date: 2026-06-18 13:09
 * @Description: https://leetcode.cn/problems/weighted-word-mapping
 * 3838. 带权单词映射
 * 给你一个字符串数组 words，其中每个字符串表示一个由小写英文字母组成的单词。
 * 同时给你一个长度为 26 的整数数组 weights，其中 weights[i] 表示第 i 个小写英文字母的权重。
 * 单词的 权重 定义为其所有字符权重的 总和。
 * 对于每个单词，将其权重对 26 取模，并将结果按字母倒序映射到一个小写英文字母（0 -> 'z', 1 -> 'y', ..., 25 -> 'a'）。
 * 返回一个由所有单词映射后的字符按顺序连接而成的字符串。
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 10
 * weights.length == 26
 * 1 <= weights[i] <= 100
 * words[i] 仅由小写英文字母组成。
 */
public class Solution {

    public String mapWordWeights(String[] words, int[] weights) {
        int n = words.length;
        char[] res = new char[n];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (char c : words[i].toCharArray()) {
                sum += weights[c - 'a'];
            }
            res[i] = (char) ('z' - sum % 26);
        }
        return String.valueOf(res);
    }

}
