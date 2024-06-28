package com.sean.leetcode.LeetCode2707;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-09 12:23
 * @Description: https://leetcode.cn/problems/extra-characters-in-a-string/
 * 2707. 字符串中的额外字符
 * 给你一个下标从 0 开始的字符串 s 和一个单词字典 dictionary 。
 * 你需要将 s 分割成若干个 互不重叠 的子字符串，每个子字符串都在 dictionary 中出现过。
 * s 中可能会有一些 额外的字符 不在任何子字符串中。
 * 请你采取最优策略分割 s ，使剩下的字符 最少 。
 */
public class Solution {

    public int minExtraChar(String s, String[] dictionary) {
        int n = s.length();
        //dp[i]为s前缀s[0...i−1]的子问题
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        Set<String> set = new HashSet<>();
        for (String str : dictionary) {
            set.add(str);
        }
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = i - 1; j >= 0; j--) {
                if (set.contains(s.substring(j, i))) {
                    dp[i] = Math.min(dp[i], dp[j]);
                }
            }
        }
        return dp[n];
    }

    class Trie {

        private Trie[] children;
        private boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new Trie();
                }
                node = node.children[c - 'a'];
            }
            node.isEnd = true;
        }

        public Trie track(char c) {
            Trie node = this;
            if (node == null || node.children[c - 'a'] == null) {
                return null;
            }
            node = node.children[c - 'a'];
            return node;
        }

        public boolean isEnd() {
            return isEnd;
        }

    }

    public int minExtraChar1(String s, String[] dictionary) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        Trie trie = new Trie();
        for (String str : dictionary) {
            trie.insert(new StringBuilder(str).reverse().toString());
        }
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            Trie node = trie;
            for (int j = i - 1; j >= 0; j--) {
                if (node != null) {
                    node = node.track(s.charAt(j));
                    if (node != null && node.isEnd) {
                        dp[i] = Math.min(dp[i], dp[j]);
                    }
                } else {
                    break;
                }
            }
        }
        return dp[n];
    }

}
