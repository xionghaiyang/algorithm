package com.sean.leetcode.LeetCodeInterview1722;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2026-03-26 15:26
 * @Description https://leetcode.cn/problems/word-transformer-lcci
 * 面试题 17.22. 单词转换
 * 给定字典中的两个词，长度相等。
 * 写一个方法，把一个词转换成另一个词， 但是一次只能改变一个字符。
 * 每一步得到的新词都必须能在字典中找到。
 * 编写一个程序，返回一个可能的转换序列。
 * 如有多个可能的转换序列，你可以返回任何一个。
 */
public class Solution {

    private Set<String> visited = new HashSet<>();

    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String key = getKey(word, i);
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }
                map.get(key).add(word);
            }
        }
        List<String> path = new ArrayList<>();
        findPath(beginWord, endWord, map, path);
        return path;
    }

    private String getKey(String word, int i) {
        char[] str = word.toCharArray();
        str[i] = '*';
        return String.valueOf(str);
    }

    private boolean findPath(String beginWord, String endWord, Map<String, List<String>> map, List<String> path) {
        if (visited.contains(beginWord)) {
            return false;
        }
        visited.add(beginWord);
        path.add(beginWord);
        if (beginWord.equals(endWord)) {
            return true;
        }
        for (int i = 0; i < beginWord.length(); i++) {
            String key = getKey(beginWord, i);
            if (!map.containsKey(key)) {
                continue;
            }
            for (String next : map.get(key)) {
                if (findPath(next, endWord, map, path)) {
                    return true;
                }
            }
        }
        path.remove(path.size() - 1);
        return false;
    }

}
