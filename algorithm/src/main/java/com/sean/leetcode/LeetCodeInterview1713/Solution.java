package com.sean.leetcode.LeetCodeInterview1713;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-03-27 08:29
 * @Description https://leetcode.cn/problems/re-space-lcci
 * 面试题 17.13. 恢复空格
 * 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。
 * 像句子"I reset the computer. It still didn’t boot!"已经变成了"iresetthecomputeritstilldidntboot"。
 * 在处理标点符号和大小写之前，你得先把它断成词语。
 * 当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。
 * 假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
 * 注意：本题相对原题稍作改动，只需返回未识别的字符数
 * 0 <= len(sentence) <= 1000
 * dictionary中总字符数不超过 150000。
 * 你可以认为dictionary和sentence中只包含小写字母。
 */
public class Solution {

    public class Trie {
        private boolean isEnd;
        private Trie[] children;

        public Trie() {
            isEnd = false;
            children = new Trie[26];
        }

        public void insert(String word) {
            Trie cur = this;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (cur.children[index] == null) {
                    cur.children[index] = new Trie();
                }
                cur = cur.children[index];
            }
            cur.isEnd = true;
        }
    }

    public int respace(String[] dictionary, String sentence) {
        Trie root = new Trie();
        for (String word : dictionary) {
            root.insert(word);
        }
        int[] memo = new int[sentence.length()];
        Arrays.fill(memo, -1);
        return process(sentence, root, memo, 0);
    }

    private int process(String sentence, Trie root, int[] memo, int i) {
        if (i == sentence.length()) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        Trie cur = root;
        int res = 1 + process(sentence, root, memo, i + 1);
        for (int j = i; j < sentence.length(); j++) {
            int index = sentence.charAt(j) - 'a';
            if (cur.children[index] == null) {
                break;
            }
            if (cur.children[index].isEnd) {
                res = Math.min(res, process(sentence, root, memo, j + 1));
            }
            cur = cur.children[index];
        }
        memo[i] = res;
        return res;
    }

}
