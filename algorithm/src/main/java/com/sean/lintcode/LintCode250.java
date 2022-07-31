package com.sean.lintcode;

import java.util.*;

public class LintCode250 {

    public static boolean ispalindrome(List<String> ambigram, String word) {
        int left = 0, right = word.length() - 1;
        Set<Character> leftSet = new HashSet<>();
        Set<Character> rightSet = new HashSet<>();
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                leftSet = getReplaceList(word.charAt(left), ambigram);
                rightSet = getReplaceList(word.charAt(right), ambigram);
                if (!hasCommonItem(leftSet, rightSet)) {
                    return false;
                }
            }
            left++;
            right--;
        }
        return true;
    }

    private static Set<Character> getReplaceList(Character c, List<String> ambigram) {
        Set<Character> set = new HashSet<>();
        set.add(c);
        for (String s : ambigram) {
            if (s.charAt(0) == c) {
                set.add(s.charAt(1));
            } else if (s.charAt(1) == c) {
                set.add(s.charAt(0));
            }
        }
        return set;
    }

    private static boolean hasCommonItem(Set<Character> leftSet, Set<Character> rightSet) {
        for (Character left : leftSet) {
            for (Character right : rightSet) {
                if (left == right) {
                    return true;
                }
            }
        }
        return false;
    }

}
