package com.sean.leetcode.LeetCode2707;

import java.util.*;

/**
 * @Author: xionghaiyang
 * @Date: 2024-01-09 12:23
 * @Description: https://leetcode.cn/problems/extra-characters-in-a-string
 * 2707. 字符串中的额外字符
 * 给你一个下标从 0 开始的字符串 s 和一个单词字典 dictionary 。
 * 你需要将 s 分割成若干个 互不重叠 的子字符串，每个子字符串都在 dictionary 中出现过。
 * s 中可能会有一些 额外的字符 不在任何子字符串中。
 * 请你采取最优策略分割 s ，使剩下的字符 最少 。
 * 1 <= s.length <= 50
 * 1 <= dictionary.length <= 50
 * 1 <= dictionary[i].length <= 50
 * dictionary[i] 和 s 只包含小写英文字母。
 * dictionary 中的单词互不相同。
 */
public class Solution {

    public int minExtraChar(String s, String[] dictionary) {
        Set<String> set = new HashSet<>();
        for (String word : dictionary) {
            set.add(word);
        }
        int n = s.length();
        //dp[i]:长度为i的前缀的子问题
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 0; j < i; j++) {
                if (set.contains(s.substring(j, i))) {
                    dp[i] = Math.min(dp[i], dp[j]);
                }
            }
        }
        return dp[n];
    }

    public class Trie {
        private Trie[] children;
        private boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            int n = word.length();
            Trie node = this;
            for (int i = n - 1; i >= 0; i--) {
                int index = word.charAt(i) - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public Trie track(char c) {
            return this.children[c - 'a'];
        }

        public boolean isEnd() {
            return isEnd;
        }
    }

    public int minExtraChar1(String s, String[] dictionary) {
        Trie trie = new Trie();
        for (String word : dictionary) {
            trie.insert(word);
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
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
