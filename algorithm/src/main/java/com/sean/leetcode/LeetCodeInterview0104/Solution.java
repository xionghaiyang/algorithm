package com.sean.leetcode.LeetCodeInterview0104;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-23 12:38
 * @Description: https://leetcode.cn/problems/palindrome-permutation-lcci/?favorite=xb9lfcwi
 * 面试题 01.04. 回文排列
 * 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
 * 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
 * 回文串不一定是字典当中的单词。
 */
public class Solution {

    public boolean canPermutePalindrome(String s) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int res = 0;
        for (Character key : map.keySet()) {
            if (map.get(key) % 2 != 0) {
                res++;
            }
        }
        return res == 0 || res == 1;
    }

}
