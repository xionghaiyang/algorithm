package com.sean.leetcode.LeetCode1255;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-02-27 08:58
 * @Description: https://leetcode.cn/problems/maximum-score-words-formed-by-letters/
 * 1255. 得分最高的单词集合
 * 你将会得到一份单词表 words，一个字母表 letters （可能会有重复字母），以及每个字母对应的得分情况表 score。
 * 请你帮忙计算玩家在单词拼写游戏中所能获得的「最高得分」：能够由 letters 里的字母拼写出的 任意 属于 words 单词子集中，分数最高的单词集合的得分。
 * 单词拼写游戏的规则概述如下：
 * 玩家需要用字母表 letters 里的字母来拼写单词表 words 中的单词。
 * 可以只使用字母表 letters 中的部分字母，但是每个字母最多被使用一次。
 * 单词表 words 中每个单词只能计分（使用）一次。
 * 根据字母得分情况表score，字母 'a', 'b', 'c', ... , 'z' 对应的得分分别为 score[0], score[1], ..., score[25]。
 * 本场游戏的「得分」是指：玩家所拼写出的单词集合里包含的所有字母的得分之和。
 */
public class Solution {

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] letterMap = new int[26];
        for (char letter : letters) {
            letterMap[letter - 'a']++;
        }
        return process(words, 0, letterMap, score, 0);
    }

    private int process(String[] words, int i, int[] letterMap, int[] score, int pre) {
        if (i == words.length) {
            return pre;
        }
        int res = process(words, i + 1, letterMap, score, pre);
        String word = words[i];
        int[] help = new int[26];
        for (int j = 0; j < 26; j++) {
            help[j] = letterMap[j];
        }
        int sc = 0;
        boolean flag = true;
        for (int j = 0; j < word.length(); j++) {
            char c = word.charAt(j);
            sc += score[c - 'a'];
            help[c - 'a']--;
            if (help[c - 'a'] < 0) {
                flag = false;
                break;
            }
        }
        if (flag) {
            res = Math.max(res, process(words, i + 1, help, score, pre + sc));
        }
        return res;
    }

}
