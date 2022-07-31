package com.sean.leetcode;

import java.util.HashSet;
import java.util.Set;

public class LeetCode824 {

    public static String toGoatLatin(String sentence) {
        Set<Character> set = new HashSet<Character>() {{
            add('a');
            add('e');
            add('i');
            add('o');
            add('u');
            add('A');
            add('E');
            add('I');
            add('O');
            add('U');
        }};
        int n = sentence.length();
        int i = 0, cnt = 1;
        StringBuffer ans = new StringBuffer();
        while (i < n) {
            int j = i;
            while (j < n && sentence.charAt(j) != ' ') {
                j++;
            }
            cnt++;
            if (cnt != 2) {
                ans.append(' ');
            }
            if (set.contains(sentence.charAt(i))) {
                ans.append(sentence.substring(i, j));
            } else {
                ans.append(sentence.substring(i + 1, j));
                ans.append(sentence.charAt(i));
            }
            ans.append('m');
            for (int k = 0; k < cnt; k++) {
                ans.append('a');
            }
            i = j + 1;
        }
        return ans.toString();
    }

}
