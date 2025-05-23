package com.sean.leetcode.LeetCode2942;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-05-24 06:09
 * @Description https://leetcode.cn/problems/find-words-containing-character
 * 2942. 查找包含给定字符的单词
 * 给你一个下标从 0 开始的字符串数组 words 和一个字符 x 。
 * 请你返回一个 下标数组 ，表示下标在数组中对应的单词包含字符 x 。
 * 注意 ，返回的数组可以是 任意 顺序。
 * 1 <= words.length <= 50
 * 1 <= words[i].length <= 50
 * x 是一个小写英文字母。
 * words[i] 只包含小写英文字母。
 */
public class Solution {

    public List<Integer> findWordsContaining(String[] words, char x) {
        int n = words.length;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (char c : words[i].toCharArray()) {
                if (c == x) {
                    res.add(i);
                    break;
                }
            }
        }
        return res;
    }

    public List<Integer> findWordsContaining1(String[] words, char x) {
        int n = words.length;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (words[i].indexOf(x) != -1) {
                res.add(i);
            }
        }
        return res;
    }

}
