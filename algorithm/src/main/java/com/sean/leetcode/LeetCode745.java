package com.sean.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode745 {

    class WordFilter1 {

        Map<String, Integer> dictionary;

        public WordFilter1(String[] words) {
            dictionary = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                int len = word.length();
                for (int prefixLength = 1; prefixLength <= len; prefixLength++) {
                    for (int suffixLength = 1; suffixLength <= len; suffixLength++) {
                        dictionary.put(word.substring(0, prefixLength) + "#" + word.substring(len - suffixLength), i);
                    }
                }
            }
        }

        public int f(String pref, String suff) {
            return dictionary.getOrDefault(pref + "#" + suff, -1);
        }
    }


    class WordFilter {
        Trie trie;
        String weightKey;

        public WordFilter(String[] words) {
            trie = new Trie();
            weightKey = "##";
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                Trie cur = trie;
                int m = word.length();
                for (int j = 0; j < m; j++) {
                    Trie tmp = cur;
                    for (int k = j; k < m; k++) {
                        String key = new StringBuilder().append(word.charAt(k)).append('#').toString();
                        if (!tmp.children.containsKey(key)) {
                            tmp.children.put(key, new Trie());
                        }
                        tmp = tmp.children.get(key);
                        tmp.weight.put(weightKey, i);
                    }
                    tmp = cur;
                    for (int k = j; k < m; k++) {
                        String key = new StringBuilder().append('#').append(word.charAt(m - k - 1)).toString();
                        if (!tmp.children.containsKey(key)) {
                            tmp.children.put(key, new Trie());
                        }
                        tmp = tmp.children.get(key);
                        tmp.weight.put(weightKey, i);
                    }
                    String key = new StringBuilder().append(word.charAt(j)).append(word.charAt(m - j - 1)).toString();
                    if (!cur.children.containsKey(key)) {
                        cur.children.put(key, new Trie());
                    }
                    cur = cur.children.get(key);
                    cur.weight.put(weightKey, i);
                }
            }
        }

        public int f(String pref, String suff) {
            Trie cur = trie;
            int prefixLenth = pref.length();
            int suffixLenth = suff.length();
            int maxLenth = Math.max(prefixLenth, suffixLenth);
            for (int i = 0; i < maxLenth; i++) {
                char ch1 = i < prefixLenth ? pref.charAt(i) : '#';
                char ch2 = i < suffixLenth ? suff.charAt(suffixLenth - 1 - i) : '#';
                String key = new StringBuilder().append(ch1).append(ch2).toString();
                if (!cur.children.containsKey(key)) {
                    return -1;
                }
                cur = cur.children.get(key);
            }
            return cur.weight.get(weightKey);
        }
    }

    class Trie {

        Map<String, Trie> children;
        Map<String, Integer> weight;

        public Trie() {
            children = new HashMap<>();
            weight = new HashMap<>();
        }
    }

}
