package com.sean.leetcode.LeetCode1684;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-08 09:17
 * @Description: https://leetcode.cn/problems/count-the-number-of-consistent-strings/
 * 1684. 统计一致字符串的数目
 * 给你一个由不同字符组成的字符串 allowed 和一个字符串数组 words 。
 * 如果一个字符串的每一个字符都在 allowed 中，就称这个字符串是 一致字符串 。
 * 请你返回 words 数组中 一致字符串 的数目。
 */
public class Solution {

    public int countConsistentStrings1(String allowed, String[] words) {
        if (words == null || words.length == 0 || allowed == null || allowed.length() == 0) {
            return 0;
        }
        int n = allowed.length();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(allowed.charAt(i));
        }
        int res = 0;
        boolean flag = true;
        for (String word : words) {
            flag = true;
            for (int i = 0; i < word.length(); i++) {
                if (!set.contains(word.charAt(i))) {
                    flag = false;
                }
            }
            if (flag) {
                res++;
            }
        }
        return res;
    }

    public int countConsistentStrings(String allowed, String[] words) {
        if (words == null || words.length == 0 || allowed == null || allowed.length() == 0) {
            return 0;
        }
        int n = allowed.length();
        int mask = 0;
        for (int i = 0; i < n; i++) {
            char c = allowed.charAt(i);
            mask |= 1 << (c - 'a');
        }
        int res = 0;
        int mask1 = 0;
        for (String word : words) {
            mask1 = 0;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                mask1 |= 1 << (c - 'a');
            }
            if ((mask1 | mask) == mask) {
                res++;
            }
        }
        return res;
    }

}
