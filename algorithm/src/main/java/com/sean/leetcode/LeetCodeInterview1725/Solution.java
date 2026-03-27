package com.sean.leetcode.LeetCodeInterview1725;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2026-03-27 13:41
 * @Description https://leetcode.cn/problems/word-rectangle-lcci
 * 面试题 17.25. 单词矩阵
 * 给定一份单词的清单，设计一个算法，创建由字母组成的面积最大的矩形，其中每一行组成一个单词(自左向右)，每一列也组成一个单词(自上而下)。
 * 不要求这些单词在清单里连续出现，但要求所有行等长，所有列等高。
 * 如果有多个面积最大的矩形，输出任意一个均可。
 * 一个单词可以重复使用。
 * words.length <= 1000
 * words[i].length <= 100
 * 数据保证单词足够随机
 */
public class Solution {

    public class Trie {
        private boolean isEnd;
        private Trie[] children;

        public Trie() {
            isEnd = false;
            children = new Trie[26];
        }

        public void insert(String word) {
            Trie cur = this;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (cur.children[index] == null) {
                    cur.children[index] = new Trie();
                }
                cur = cur.children[index];
            }
            cur.isEnd = true;
        }
    }

    private int maxArea = 0;
    private List<String> ans;

    public String[] maxRectangle(String[] words) {
        ans = new ArrayList<>();
        Trie root = new Trie();
        Map<Integer, List<String>> map = new HashMap<>();
        for (String word : words) {
            root.insert(word);
            int len = word.length();
            map.computeIfAbsent(len, k -> new ArrayList<>()).add(word);
        }
        Arrays.sort(words, (a, b) -> b.length() - a.length());
        int n = words.length;
        for (int i = 0; i < n; i++) {
            String word = words[i];
            int wordLength = word.length();
            if (wordLength * wordLength <= maxArea) {
                break;
            }
            List<String> curRect = new ArrayList<>();
            curRect.add(word);
            List<Trie> tries = new ArrayList<>();
            //是否能作为矩形的第一行
            boolean isRectValid = true;
            //是否有潜力组成更大的矩形
            boolean hasMorePotential = true;
            for (int j = 0; j < wordLength; j++) {
                int index = word.charAt(j) - 'a';
                if (root.children[index] != null) {
                    if (!root.children[index].isEnd) {
                        isRectValid = false;
                    }
                    tries.add(root.children[index]);
                } else {
                    isRectValid = false;
                    hasMorePotential = false;
                    break;
                }
            }
            if (hasMorePotential) {
                process(1, wordLength, curRect, tries, map.get(wordLength));
            }
            if (isRectValid && wordLength > maxArea) {
                maxArea = wordLength;
                ans = new ArrayList<>(curRect);
            }
        }
        String[] res = new String[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    private void process(int height, int wordLength, List<String> curRect, List<Trie> tries, List<String> words) {
        if (height == wordLength) {
            return;
        }
        for (String word : words) {
            boolean isRectValid = true;
            boolean hasMorePotential = true;
            List<String> nextRect = new ArrayList<>();
            List<Trie> nextTries = new ArrayList<>();
            for (int i = 0; i < wordLength; i++) {
                int index = word.charAt(i) - 'a';
                Trie cur = tries.get(i);
                if (cur.children[index] != null) {
                    if (!cur.children[index].isEnd) {
                        isRectValid = false;
                    }
                    nextTries.add(cur.children[index]);
                } else {
                    isRectValid = false;
                    hasMorePotential = false;
                    break;
                }
            }
            if (hasMorePotential) {
                nextRect.addAll(curRect);
                nextRect.add(word);
                process(height + 1, wordLength, nextRect, nextTries, words);
            }
            int area = wordLength * (height + 1);
            if (isRectValid && area > maxArea) {
                maxArea = area;
                ans = new ArrayList<>(nextRect);
            }
        }
    }

}
