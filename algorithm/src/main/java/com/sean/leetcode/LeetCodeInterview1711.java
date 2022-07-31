package com.sean.leetcode;

public class LeetCodeInterview1711 {

    public static int findClosest(String[] words, String word1, String word2) {
        int index1 = -1, index2 = -1;
        int res = words.length;
        for (int i = 0; i < words.length; i++) {
            if (word1.equals(words[i])) {
                index1 = i;
            }
            if (word2.equals(words[i])) {
                index2 = i;
            }
            if (index1 >= 0 && index2 >= 0) {
                res = Math.min(res, Math.abs(index1 - index2));
            }
        }
        return res;
    }

}
