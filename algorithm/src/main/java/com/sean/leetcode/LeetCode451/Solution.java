package com.sean.leetcode.LeetCode451;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-02 18:30
 * @Description: https://leetcode.cn/problems/sort-characters-by-frequency/description/
 * 451. 根据字符出现频率排序
 * 给定一个字符串 s ，根据字符出现的 频率 对其进行 降序排序 。
 * 一个字符出现的 频率 是它出现在字符串中的次数。
 * 返回 已排序的字符串 。
 * 如果有多个答案，返回其中任何一个。
 */
public class Solution {

    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Character> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (a, b) -> map.get(b) - map.get(a));
        StringBuilder res = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Character c = list.get(i);
            int freq = map.get(c);
            for (int j = 0; j < freq; j++) {
                res.append(c);
            }
        }
        return res.toString();
    }

}
