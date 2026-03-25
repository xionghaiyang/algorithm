package com.sean.leetcode.LeetCodeInterview1711;

/**
 * @Author xionghaiyang
 * @Date 2026-03-25 15:03
 * @Description https://leetcode.cn/problems/find-closest-lcci
 * 面试题 17.11. 单词距离
 * 有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。
 * 如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
 * words.length <= 100000
 */
public class Solution {

    public int findClosest(String[] words, String word1, String word2) {
        int n = words.length;
        int res = n, index1 = -1, index2 = -1;
        for (int i = 0; i < n; i++) {
            if (word1.equals(words[i])) {
                index1 = i;
                if (index2 >= 0) {
                    res = Math.min(res, Math.abs(index1 - index2));
                }
            } else if (word2.equals(words[i])) {
                index2 = i;
                if (index1 >= 0) {
                    res = Math.min(res, Math.abs(index1 - index2));
                }
            }
        }
        return res;
    }

}
