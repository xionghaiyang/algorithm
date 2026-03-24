package com.sean.leetcode.LeetCodeInterview1620;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2026-03-24 12:37
 * @Description https://leetcode.cn/problems/t9-lcci
 * 面试题 16.20. T9键盘
 * 在老式手机上，用户通过数字键盘输入，手机将提供与这些数字相匹配的单词列表。
 * 每个数字映射到0至4个字母。
 * 给定一个数字序列，实现一个算法来返回匹配单词的列表。
 * 你会得到一张含有有效单词的列表。
 * 映射如下图所示：
 * num.length <= 1000
 * words.length <= 500
 * words[i].length == num.length
 * num中不会出现 0, 1 这两个数字
 */
public class Solution {

    public class Trie {
        private boolean isEnd;
        private Trie[] children;

        public Trie() {
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
            cur.isEnd = true;
        }
    }

    private String[] strs = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    private List<String> res = new ArrayList<>();
    private StringBuilder str = new StringBuilder();

    public List<String> getValidT9Words(String num, String[] words) {
        Trie root = new Trie();
        for (String word : words) {
            root.add(word);
        }
        process(num, root, 0);
        return res;
    }

    private void process(String num, Trie trie, int i) {
        if (i == num.length()) {
            if (trie.isEnd) {
                res.add(str.toString());
            }
            return;
        }
        int id = num.charAt(i) - '0';
        for (char c : strs[id].toCharArray()) {
            if (trie.children[c - 'a'] != null) {
                str.append(c);
                process(num, trie.children[c - 'a'], i + 1);
                str.deleteCharAt(str.length() - 1);
            }
        }
    }

}
