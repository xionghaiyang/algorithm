package com.sean.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 统计一致字符串的数目
 * https://leetcode-cn.com/problems/count-the-number-of-consistent-strings/
 */
public class LeetCode1684 {

    public static int countConsistentStrings(String allowed, String[] words) {
        int res = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < allowed.length(); i++) {
            set.add(allowed.charAt(i));
        }
        for (String word : words) {
            if (matches(word, set)) {
                res++;
            }
        }
        return res;
    }

    private static boolean matches(String word, Set<Character> set) {
        for (int i = 0; i < word.length(); i++) {
            if (!set.contains(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(countConsistentStrings("ab",new String[]{"ad", "bd", "aaab", "baa", "badab"}));
        System.out.println(countConsistentStrings("abc",new String[]{"a", "b", "c", "ab", "ac", "bc", "abc"}));
        System.out.println(countConsistentStrings("cad",new String[]{"cc", "acd", "b", "ba", "bac", "bad", "ac", "d"}));
    }
}
