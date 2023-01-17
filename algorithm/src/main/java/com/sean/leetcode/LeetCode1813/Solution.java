package com.sean.leetcode.LeetCode1813;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-16 09:51
 * @Description: https://leetcode.cn/problems/sentence-similarity-iii/
 * 1813. 句子相似性 III
 * 一个句子是由一些单词与它们之间的单个空格组成，且句子的开头和结尾没有多余空格。
 * 比方说，"Hello World" ，"HELLO" ，"hello world hello world" 都是句子。
 * 每个单词都 只 包含大写和小写英文字母。
 * 如果两个句子 sentence1 和 sentence2 ，可以通过往其中一个句子插入一个任意的句子（可以是空句子）而得到另一个句子，那么我们称这两个句子是 相似的 。
 * 比方说，sentence1 = "Hello my name is Jane" 且 sentence2 = "Hello Jane" ，
 * 我们可以往 sentence2 中 "Hello" 和 "Jane" 之间插入 "my name is" 得到 sentence1 。
 * 给你两个句子 sentence1 和 sentence2 ，如果 sentence1 和 sentence2 是相似的，请你返回 true ，否则返回 false 。
 */
public class Solution {

    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] words1 = sentence1.split(" ");
        String[] words2 = sentence2.split(" ");
        int n = words1.length, m = words2.length;
        int i = 0, j = 0;
        while (i < n && i < m && words1[i].equals(words2[i])) {
            i++;
        }
        while (j < n - i && j < m - i && words1[n - j - 1].equals(words2[m - j - 1])) {
            j++;
        }
        return i + j == Math.min(n, m);
    }

}
