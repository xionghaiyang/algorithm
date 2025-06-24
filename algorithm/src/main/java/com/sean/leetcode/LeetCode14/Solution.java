package com.sean.leetcode.LeetCode14;

/**
 * @Author xionghaiyang
 * @Date 2025-06-24 19:38
 * @Description https://leetcode.cn/problems/longest-common-prefix
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 如果非空，则仅由小写英文字母组成
 */
public class Solution {

    public class Node {
        int pass;
        Node[] nexts;

        public Node() {
            pass = 0;
            nexts = new Node[26];
        }
    }

    public String longestCommonPrefix(String[] strs) {
        Node root = new Node();
        int n = strs.length;
        for (int i = 0; i < n; i++) {
            if ("".equals(strs[i])) {
                return "";
            }
            Node cur = root;
            cur.pass++;
            if (i == 0) {
                for (int j = 0; j < strs[i].length(); j++) {
                    int index = strs[i].charAt(j) - 'a';
                    if (cur.nexts[index] == null) {
                        cur.nexts[index] = new Node();
                    }
                    cur.nexts[index].pass++;
                    cur = cur.nexts[index];
                }
            } else {
                for (int j = 0; j < strs[i].length(); j++) {
                    int index = strs[i].charAt(j) - 'a';
                    if (cur.nexts[index] == null || cur.nexts[index].pass != i) {
                        break;
                    }
                    cur.nexts[index].pass++;
                    cur = cur.nexts[index];
                }
            }
        }
        Node cur = root;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            int index = c - 'a';
            if (cur.nexts[index].pass == n) {
                res.append(c);
                cur = cur.nexts[index];
            } else {
                break;
            }
        }
        return res.toString();
    }

}
