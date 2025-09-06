package com.sean.leetcode.LeetCode472;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-09-06 13:04
 * @Description https://leetcode.cn/problems/concatenated-words
 * 472. 连接词
 * 给你一个 不含重复 单词的字符串数组 words ，请你找出并返回 words 中的所有 连接词 。
 * 连接词 定义为：一个完全由给定数组中的至少两个较短单词（不一定是不同的两个单词）组成的字符串。
 * 1 <= words.length <= 10^4
 * 1 <= words[i].length <= 30
 * words[i] 仅由小写英文字母组成。
 * words 中的所有字符串都是 唯一 的。
 * 1 <= sum(words[i].length) <= 10^5
 */
public class Solution {

    public class Trie {
        private Trie[] children;
        private boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie cur = this;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (cur.children[index] == null) {
                    cur.children[index] = new Trie();
                }
                cur = cur.children[index];
            }
            cur.isEnd = true;
        }
    }

    private Trie trie = new Trie();

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        for (String word : words) {
            if (word.length() == 0) {
                continue;
            }
            if (dfs(word, 0, new boolean[word.length()])) {
                res.add(word);
            } else {
                trie.insert(word);
            }
        }
        return res;
    }

    private boolean dfs(String word, int start, boolean[] visited) {
        if (word.length() == start) {
            return true;
        }
        if (visited[start]) {
            return false;
        }
        visited[start] = true;
        Trie cur = trie;
        for (int i = start; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            cur = cur.children[index];
            if (cur == null) {
                return false;
            }
            if (cur.isEnd) {
                if (dfs(word, i + 1, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

}
