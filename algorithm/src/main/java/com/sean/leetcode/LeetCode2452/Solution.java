package com.sean.leetcode.LeetCode2452;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2026-04-22 07:49
 * @Description https://leetcode.cn/problems/words-within-two-edits-of-dictionary
 * 2452. 距离字典两次编辑以内的单词
 * 给你两个字符串数组 queries 和 dictionary 。
 * 数组中所有单词都只包含小写英文字母，且长度都相同。
 * 一次 编辑 中，你可以从 queries 中选择一个单词，将任意一个字母修改成任何其他字母。
 * 从 queries 中找到所有满足以下条件的字符串：不超过 两次编辑内，字符串与 dictionary 中某个字符串相同。
 * 请你返回 queries 中的单词列表，这些单词距离 dictionary 中的单词 编辑次数 不超过 两次 。
 * 单词返回的顺序需要与 queries 中原本顺序相同。
 * 1 <= queries.length, dictionary.length <= 100
 * n == queries[i].length == dictionary[j].length
 * 1 <= n <= 100
 * 所有 queries[i] 和 dictionary[j] 都只包含小写英文字母。
 */
public class Solution {

    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> res = new ArrayList<>();
        for (String query : queries) {
            for (String s : dictionary) {
                int cnt = 0;
                for (int i = 0; i < query.length(); i++) {
                    if (query.charAt(i) != s.charAt(i)) {
                        cnt++;
                    }
                }
                if (cnt <= 2) {
                    res.add(query);
                    break;
                }
            }
        }
        return res;
    }

    public class Trie {
        private Trie[] child;
        private boolean isEnd;

        public Trie() {
            child = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.child[index] == null) {
                    node.child[index] = new Trie();
                }
                node = node.child[index];
            }
            node.isEnd = true;
        }

        public boolean find(String word, int i, int cnt) {
            Trie node = this;
            if (i == word.length()) {
                return node.isEnd;
            }
            int index = word.charAt(i) - 'a';
            if (node.child[index] != null && node.child[index].find(word, i + 1, cnt)) {
                return true;
            }
            if (cnt < 2) {
                for (int j = 0; j < 26; j++) {
                    if (j == index) {
                        continue;
                    }
                    if (node.child[j] != null && node.child[j].find(word, i + 1, cnt + 1)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public List<String> twoEditWords1(String[] queries, String[] dictionary) {
        Trie trie = new Trie();
        for (String word : dictionary) {
            trie.insert(word);
        }
        List<String> res = new ArrayList<>();
        for (String query : queries) {
            if (trie.find(query, 0, 0)) {
                res.add(query);
            }
        }
        return res;
    }

}
