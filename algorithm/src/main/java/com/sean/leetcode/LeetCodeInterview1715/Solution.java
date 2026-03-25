package com.sean.leetcode.LeetCodeInterview1715;

/**
 * @Author xionghaiyang
 * @Date 2026-03-25 20:16
 * @Description https://leetcode.cn/problems/longest-word-lcci
 * 面试题 17.15. 最长单词
 * 给定一组单词words，编写一个程序，找出其中的最长单词，且该单词由这组单词中的其他单词组合而成。
 * 若有多个长度相同的结果，返回其中字典序最小的一项，若没有符合要求的单词则返回空字符串。
 * 0 <= len(words) <= 200
 * 1 <= len(words[i]) <= 100
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

    public String longestWord(String[] words) {
        Trie root = new Trie();
        for (String word : words) {
            root.insert(word);
        }
        int maxLength = 0;
        String res = "";
        for (String word : words) {
            if (word.length() > maxLength && check(root, word, 0)) {
                maxLength = word.length();
                res = word;
            } else if (word.length() == maxLength && word.compareTo(res) < 0 && check(root, word, 0)) {
                res = word;
            }
        }
        return res;
    }

    private boolean check(Trie root, String str, int splits) {
        if ("".equals(str)) {
            return splits > 1;
        }
        Trie cur = root;
        int n = str.length();
        for (int i = 0; i < n; i++) {
            int index = str.charAt(i) - 'a';
            if (cur.children[index] == null) {
                return false;
            }
            if (cur.children[index].isEnd && check(root, str.substring(i + 1), splits + 1)) {
                return true;
            }
            cur = cur.children[index];
        }
        return false;
    }

}
