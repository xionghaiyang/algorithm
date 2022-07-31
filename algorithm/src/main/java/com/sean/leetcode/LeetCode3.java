package com.sean.leetcode;

import java.util.HashSet;
import java.util.Set;

public class LeetCode3 {

    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int n = s.length();
        int index = -1, res = 0;
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                set.remove(s.charAt(i - 1));
            }
            while (index + 1 < n && !set.contains(s.charAt(index + 1))) {
                set.add(s.charAt(index + 1));
                index++;
            }
            res = Math.max(res, index - i + 1);
        }
        return res;
    }

}
