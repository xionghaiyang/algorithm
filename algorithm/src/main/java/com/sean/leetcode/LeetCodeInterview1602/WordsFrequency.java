package com.sean.leetcode.LeetCodeInterview1602;

/**
 * @Author xionghaiyang
 * @Date 2026-03-21 12:35
 * @Description https://leetcode.cn/problems/words-frequency-lcci
 * 面试题 16.02. 单词频率
 * 设计一个方法，找出任意指定单词在一本书中的出现频率。
 * 你的实现应该支持如下操作：
 * WordsFrequency(book)构造函数，参数为字符串数组构成的一本书
 * get(word)查询指定单词在书中出现的频率
 * book[i]中只包含小写字母
 * 1 <= book.length <= 100000
 * 1 <= book[i].length <= 10
 * get函数的调用次数不会超过100000
 */
public class WordsFrequency {

    public class Trie {
        private int cnt;
        private boolean isEnd;
        private Trie[] children;

        public Trie() {
            cnt = 0;
            isEnd = false;
            children = new Trie[26];
        }

        public void add(String word) {
            Trie cur = this;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (cur.children[index] == null) {
                    cur.children[index] = new Trie();
                }
                cur = cur.children[index];
            }
            cur.cnt++;
            cur.isEnd = true;
        }

        public int get(String word) {
            Trie cur = this;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (cur.children[index] == null) {
                    return 0;
                }
                cur = cur.children[index];
            }
            return cur.isEnd ? cur.cnt : 0;
        }
    }

    private Trie root;

    public WordsFrequency(String[] book) {
        root = new Trie();
        for (String s : book) {
            root.add(s);
        }
    }

    public int get(String word) {
        return root.get(word);
    }

}
