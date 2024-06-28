package com.sean.leetcode.LeetCode1657;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-07 20:29
 * @Description: https://leetcode.cn/problems/determine-if-two-strings-are-close/?envType=study-plan-v2&envId=leetcode-75
 * 1657. 确定两个字符串是否接近
 * 如果可以使用以下操作从一个字符串得到另一个字符串，则认为两个字符串 接近 ：
 * 操作 1：交换任意两个 现有 字符。
 * 例如，abcde -> aecdb
 * 操作 2：将一个 现有 字符的每次出现转换为另一个 现有 字符，并对另一个字符执行相同的操作。
 * 例如，aacabb -> bbcbaa（所有 a 转化为 b ，而所有的 b 转换为 a ）
 * 你可以根据需要对任意一个字符串多次使用这两种操作。
 * 给你两个字符串，word1 和 word2 。如果 word1 和 word2 接近 ，就返回 true ；否则，返回 false 。
 */
public class Solution {

    class Info {
        Map<Integer, Integer> map;
        Set<Character> set;

        public Info(Map<Integer, Integer> map, Set<Character> set) {
            this.map = map;
            this.set = set;
        }
    }

    public boolean closeStrings1(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        Info info1 = process(word1);
        Info info2 = process(word2);
        for (int key : info1.map.keySet()) {
            if (!info2.map.containsKey(key) || !info1.map.get(key).equals(info2.map.get(key))) {
                return false;
            }
        }
        for (Character c : info1.set) {
            if (!info2.set.contains(c)) {
                return false;
            }
        }
        return true;
    }

    private Info process(String word) {
        Map<Character, Integer> preq = new HashMap<>();
        int n = word.length();
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            preq.put(c, preq.getOrDefault(c, 0) + 1);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (Character key : preq.keySet()) {
            int value = preq.get(key);
            map.put(value, map.getOrDefault(value, 0) + 1);
        }
        return new Info(map, preq.keySet());
    }

    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int n = word1.length();
        int[] map1 = new int[26];
        int[] map2 = new int[26];
        for (int i = 0; i < n; i++) {
            map1[word1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            map2[word2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if ((map1[i] == 0 && map2[i] != 0) || (map1[i] != 0 && map2[i] == 0)) {
                return false;
            }
        }
        Arrays.sort(map1);
        Arrays.sort(map2);
        for (int i = 0; i < 26; i++) {
            if (map1[i] != map2[i]) {
                return false;
            }
        }
        return true;
    }

}
