package com.sean.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LeetCode1048 {

    public int longestStrChain(String[] words) {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        Map<String, Integer> map = new HashMap<>();
        int res = 0;
        for (String word : words) {
            int count = 1;
            for (int i = 0; i < word.length(); i++) {
                StringBuilder sb = new StringBuilder(word);
                sb.deleteCharAt(i);
                String str = sb.toString();
                if (map.containsKey(str)) {
                    count = Math.max(count, map.get(str) + 1);
                }
            }
            map.put(word, count);
            res = Math.max(res, count);
        }
        return res;
    }

}
