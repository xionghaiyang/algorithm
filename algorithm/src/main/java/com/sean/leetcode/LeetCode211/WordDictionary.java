package com.sean.leetcode.LeetCode211;

/**
 * @Author xionghaiyang
 * @Date 2025-07-19 17:14
 * @Description https://leetcode.cn/problems/design-add-and-search-words-data-structure
 * 211. 添加与搜索单词 - 数据结构设计
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 * 实现词典类 WordDictionary ：
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。
 * word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 * 1 <= word.length <= 25
 * addWord 中的 word 由小写英文字母组成
 * search 中的 word 由 '.' 或小写英文字母组成
 * 最多调用 10^4 次 addWord 和 search
 */
public class WordDictionary {

    public class Trie {
        private Trie[] children;
        private boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void add(String word) {
            Trie cur = this;
            int n = word.length();
            for (int i = 0; i < n; i++) {
                int index = word.charAt(i) - 'a';
                if (cur.children[index] == null) {
                    cur.children[index] = new Trie();
                }
                cur = cur.children[index];
            }
            cur.isEnd = true;
        }

        public boolean search(String word, int i) {
            Trie cur = this;
            if (i == word.length()) {
                return cur.isEnd;
            }
            char c = word.charAt(i);
            if (c == '.') {
                for (int j = 0; j < 26; j++) {
                    if (cur.children[j] != null && cur.children[j].search(word, i + 1)) {
                        return true;
                    }
                }
            } else {
                int j = c - 'a';
                if (cur.children[j] != null && cur.children[j].search(word, i + 1)) {
                    return true;
                }
            }
            return false;
        }
    }

    private Trie trie;

    public WordDictionary() {
        trie = new Trie();
    }

    public void addWord(String word) {
        trie.add(word);
    }

    public boolean search(String word) {
        return trie.search(word, 0);
    }

}
