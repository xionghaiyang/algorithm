package com.sean.leetcode.LeetCode3093;

/**
 * @Author: xionghaiyang
 * @Date: 2026-05-28 06:02
 * @Description: https://leetcode.cn/problems/longest-common-suffix-queries
 * 3093. 最长公共后缀查询
 * 给你两个字符串数组 wordsContainer 和 wordsQuery 。
 * 对于每个 wordsQuery[i] ，你需要从 wordsContainer 中找到一个与 wordsQuery[i] 有 最长公共后缀 的字符串。
 * 如果 wordsContainer 中有两个或者更多字符串有最长公共后缀，那么答案为长度 最短 的。
 * 如果有超过两个字符串有 相同 最短长度，那么答案为它们在 wordsContainer 中出现 更早 的一个。
 * 请你返回一个整数数组 ans ，其中 ans[i]是 wordsContainer中与 wordsQuery[i] 有 最长公共后缀 字符串的下标。
 * 1 <= wordsContainer.length, wordsQuery.length <= 10^4
 * 1 <= wordsContainer[i].length <= 5 * 10^3
 * 1 <= wordsQuery[i].length <= 5 * 10^3
 * wordsContainer[i] 只包含小写英文字母。
 * wordsQuery[i] 只包含小写英文字母。
 * wordsContainer[i].length 的和至多为 5 * 10^5 。
 * wordsQuery[i].length 的和至多为 5 * 10^5 。
 */
public class Solution {

    public class Node {
        private Node[] children;
        private int minLen;
        private int minIndex;

        public Node() {
            children = new Node[26];
            minLen = Integer.MAX_VALUE;
            minIndex = -1;
        }

        public void insert(String word, int idx) {
            int n = word.length();
            Node cur = this;
            if (n < cur.minLen) {
                cur.minLen = n;
                cur.minIndex = idx;
            }
            for (int i = n - 1; i >= 0; i--) {
                int index = word.charAt(i) - 'a';
                if (cur.children[index] == null) {
                    cur.children[index] = new Node();
                }
                cur = cur.children[index];
                if (n < cur.minLen) {
                    cur.minLen = n;
                    cur.minIndex = idx;
                }
            }
        }

        public int query(String word) {
            int m = word.length();
            Node cur = this;
            for (int i = m - 1; i >= 0; i--) {
                int index = word.charAt(i) - 'a';
                if (cur.children[index] == null) {
                    break;
                }
                cur = cur.children[index];
            }
            return cur.minIndex;
        }
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        Node node = new Node();
        int n = wordsContainer.length, m = wordsQuery.length;
        for (int i = 0; i < n; i++) {
            node.insert(wordsContainer[i], i);
        }
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            res[i] = node.query(wordsQuery[i]);
        }
        return res;
    }

}
