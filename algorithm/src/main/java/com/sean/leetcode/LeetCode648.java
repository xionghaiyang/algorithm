package com.sean.leetcode;

import java.util.*;

public class LeetCode648 {

    public String replaceWords1(List<String> dictionary, String sentence) {
        Set<String> set = new HashSet<>();
        for (String s : dictionary) {
            set.add(s);
        }
        StringBuffer res = new StringBuffer();
        String[] strs = sentence.split(" ");
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            for (int j = 0; j < str.length(); j++) {
                if (set.contains(str.substring(0, j + 1))) {
                    res.append(str.substring(0, j + 1));
                    break;
                }
                if (j == str.length() - 1) {
                    res.append(str);
                }
            }
            if (i < strs.length - 1) {
                res.append(" ");
            }
        }
        return res.toString();
    }

    class Trie {

        Map<Character, Trie> children;

        public Trie() {
            children = new HashMap<>();
        }
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        for (String word : dictionary) {
            Trie cur = trie;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                cur.children.putIfAbsent(c, new Trie());
                cur = cur.children.get(c);
            }
            cur.children.put('#', new Trie());
        }
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = findRoot(words[i], trie);
        }
        return String.join(" ", words);
    }

    private String findRoot(String word, Trie trie) {
        StringBuffer res = new StringBuffer();
        Trie cur = trie;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.children.containsKey('#')) {
                return res.toString();
            }
            if (!cur.children.containsKey(c)) {
                return word;
            }
            res.append(c);
            cur = cur.children.get(c);
        }
        return res.toString();
    }

}
