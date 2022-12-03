package com.sean.leetcode.LeetCode1455;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-01 08:33
 * @Description: https://leetcode.cn/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/
 * 1455. 检查单词是否为句中其他单词的前缀
 * 给你一个字符串 sentence 作为句子并指定检索词为 searchWord ，其中句子由若干用 单个空格 分隔的单词组成。
 * 请你检查检索词 searchWord 是否为句子 sentence 中任意单词的前缀。
 * 如果 searchWord 是某一个单词的前缀，则返回句子 sentence 中该单词所对应的下标（下标从 1 开始）。
 * 如果 searchWord 是多个单词的前缀，则返回匹配的第一个单词的下标（最小下标）。
 * 如果 searchWord 不是任何单词的前缀，则返回 -1 。
 * 字符串 s 的 前缀 是 s 的任何前导连续子字符串。
 */
public class Solution {

    public class Node {
        public List<Integer> pass;
        public Node[] nexts;

        public Node() {
            nexts = new Node[26];
        }
    }

    public class Trie {
        private Node root;
        private int idx;

        public Trie() {
            root = new Node();
            idx = 0;
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            idx++;
            char[] str = word.toCharArray();
            Node node = root;
            if (node.pass == null) {
                node.pass = new ArrayList<>();
            }
            node.pass.add(idx);
            int index;
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node();
                }
                node = node.nexts[index];
                if (node.pass == null) {
                    node.pass = new ArrayList<>();
                }
                node.pass.add(idx);
            }
        }

        public int prefixNumber(String pre) {
            if (pre == null) {
                return -1;
            }
            char[] str = pre.toCharArray();
            Node node = root;
            int index;
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';
                if (node.nexts[index] == null) {
                    return -1;
                }
                node = node.nexts[index];
            }
            if (node.pass != null) {
                return node.pass.get(0);
            } else {
                return -1;
            }
        }

    }

    public int isPrefixOfWord(String sentence, String searchWord) {
        Trie trie = new Trie();
        String[] strs = sentence.split(" ");
        for (String str : strs) {
            trie.insert(str);
        }
        return trie.prefixNumber(searchWord);
    }

}
