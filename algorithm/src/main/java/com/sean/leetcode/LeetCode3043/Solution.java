package com.sean.leetcode.LeetCode3043;

/**
 * @Author: xionghaiyang
 * @Date: 2026-05-21 10:45
 * @Description: https://leetcode.cn/problems/find-the-length-of-the-longest-common-prefix
 * 3043. 最长公共前缀的长度
 * 给你两个 正整数 数组 arr1 和 arr2 。
 * 正整数的 前缀 是其 最左边 的一位或多位数字组成的整数。
 * 例如，123 是整数 12345 的前缀，而 234 不是 。
 * 设若整数 c 是整数 a 和 b 的 公共前缀 ，那么 c 需要同时是 a 和 b 的前缀。
 * 例如，5655359 和 56554 有公共前缀 565 和 5655，而 1223 和 43456 没有 公共前缀。
 * 你需要找出属于 arr1 的整数 x 和属于 arr2 的整数 y 组成的所有数对 (x, y) 之中最长的公共前缀的长度。
 * 返回所有数对之中最长公共前缀的长度。
 * 如果它们之间不存在公共前缀，则返回 0 。
 * 1 <= arr1.length, arr2.length <= 5 * 10^4
 * 1 <= arr1[i], arr2[i] <= 10^8
 */
public class Solution {

    public class Trie {
        private Trie[] children;

        public Trie() {
            children = new Trie[10];
        }

        public void insert(int num) {
            String str = String.valueOf(num);
            Trie cur = this;
            for (int i = 0; i < str.length(); i++) {
                int index = str.charAt(i) - '0';
                if (cur.children[index] == null) {
                    cur.children[index] = new Trie();
                }
                cur = cur.children[index];
            }
        }

        public int getMaxPrefixLength(int num) {
            String str = String.valueOf(num);
            Trie cur = this;
            int res = 0;
            for (int i = 0; i < str.length(); i++) {
                int index = str.charAt(i) - '0';
                if (cur.children[index] == null) {
                    break;
                }
                cur = cur.children[index];
                res++;
            }
            return res;
        }
    }

    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Trie root = new Trie();
        for (int num : arr1) {
            root.insert(num);
        }
        int res = 0;
        for (int num : arr2) {
            res = Math.max(res, root.getMaxPrefixLength(num));
        }
        return res;
    }

}
