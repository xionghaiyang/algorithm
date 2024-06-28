package com.sean.leetcode.LeetCode2586;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-11-07 17:28
 * @Description: https://leetcode.cn/problems/count-the-number-of-vowel-strings-in-range/?envType=daily-question&envId=2023-11-07
 * 2586. 统计范围内的元音字符串数
 * 给你一个下标从 0 开始的字符串数组 words 和两个整数：left 和 right 。
 * 如果字符串以元音字母开头并以元音字母结尾，那么该字符串就是一个 元音字符串 ，
 * 其中元音字母是 'a'、'e'、'i'、'o'、'u' 。
 * 返回 words[i] 是元音字符串的数目，其中 i 在闭区间 [left, right] 内。
 */
public class Solution {

    public int vowelStrings(String[] words, int left, int right) {
        Set<Character> set = new HashSet<Character>() {{
            add('a');
            add('e');
            add('i');
            add('o');
            add('u');
        }};
        int res = 0;
        int n = words.length;
        for (int i = left; i <= right && i < n; i++) {
            String word = words[i];
            if (set.contains(word.charAt(0)) && set.contains(word.charAt(word.length() - 1))) {
                res++;
            }
        }
        return res;
    }

}
