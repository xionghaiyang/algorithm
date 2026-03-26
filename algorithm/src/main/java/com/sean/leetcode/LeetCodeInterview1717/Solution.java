package com.sean.leetcode.LeetCodeInterview1717;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2026-03-26 12:15
 * @Description https://leetcode.cn/problems/multi-search-lcci
 * 面试题 17.17. 多次搜索
 * 给定一个较长字符串big和一个包含较短字符串的数组smalls，设计一个方法，根据smalls中的每一个较短字符串，对big进行搜索。
 * 输出smalls中的字符串在big里出现的所有位置positions，其中positions[i]为smalls[i]出现的所有位置。
 * 0 <= len(big) <= 1000
 * 0 <= len(smalls[i]) <= 1000
 * smalls的总字符数不会超过 10^6。
 * 你可以认为smalls中没有重复字符串。
 * 所有出现的字符均为英文小写字母。
 */
public class Solution {

    public class Trie {
        private boolean isEnd;
        private int index;
        private Trie[] children;

        public Trie() {
            isEnd = false;
            index = -1;
            children = new Trie[26];
        }

        public void insert(String word, int i) {
            Trie cur = this;
            for (char c : word.toCharArray()) {
                int j = c - 'a';
                if (cur.children[j] == null) {
                    cur.children[j] = new Trie();
                }
                cur = cur.children[j];
            }
            cur.isEnd = true;
            cur.index = i;
        }
    }

    public int[][] multiSearch(String big, String[] smalls) {
        Trie root = new Trie();
        int n = smalls.length;
        for (int i = 0; i < n; i++) {
            root.insert(smalls[i], i);
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        int m = big.length();
        for (int i = 0; i < m; i++) {
            int j = i;
            Trie cur = root;
            while (j < m) {
                int k = big.charAt(j) - 'a';
                if (cur.children[k] == null) {
                    break;
                }
                if (cur.children[k].isEnd) {
                    int index = cur.children[k].index;
                    if (!map.containsKey(index)) {
                        map.put(index, new ArrayList<>());
                    }
                    map.get(index).add(i);
                }
                cur = cur.children[k];
                j++;
            }
        }
        int[][] res = new int[n][];
        for (int i = 0; i < n; i++) {
            res[i] = list2Arr(map.get(i));
        }
        return res;
    }

    private int[] list2Arr(List<Integer> list) {
        if (list == null) {
            return new int[0];
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

}
