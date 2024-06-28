package com.sean.leetcode.LeetCode820;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-13 13:02
 * @Description: https://leetcode.cn/problems/short-encoding-of-words/
 * 820. 单词的压缩编码
 * 单词数组 words 的 有效编码 由任意助记字符串 s 和下标数组 indices 组成，且满足：
 * words.length == indices.length
 * 助记字符串 s 以 '#' 字符结尾
 * 对于每个下标 indices[i] ，s 的一个从 indices[i] 开始、到下一个 '#' 字符结束（但不包括 '#'）的 子字符串 恰好与 words[i] 相等
 * 给你一个单词数组 words ，返回成功对 words 进行编码的最小助记字符串 s 的长度 。
 */
public class Solution {

    public int minimumLengthEncoding(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            for (int k = 1; k < word.length(); k++) {
                set.remove(word.substring(k));
            }
        }
        int res = 0;
        for (String word : set) {
            res += word.length() + 1;
        }
        return res;
    }

    class TrieNode {
        TrieNode[] children;
        int count;

        public TrieNode() {
            children = new TrieNode[26];
            count = 0;
        }

        public TrieNode get(char c) {
            if (children[c - 'a'] == null) {
                children[c - 'a'] = new TrieNode();
                count++;
            }
            return children[c - 'a'];
        }
    }

    public int minimumLengthEncoding1(String[] words) {
        int n = words.length;
        TrieNode trie = new TrieNode();
        Map<TrieNode, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String word = words[i];
            TrieNode cur = trie;
            for (int j = word.length() - 1; j >= 0; j--) {
                cur = cur.get(word.charAt(j));
            }
            map.put(cur, i);
        }
        int res = 0;
        for (TrieNode node : map.keySet()) {
            if (node.count == 0) {
                res += words[map.get(node)].length() + 1;
            }
        }
        return res;
    }

}
