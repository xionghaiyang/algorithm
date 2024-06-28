package com.sean.leetcode;

/**
 * 有效的字母异位词
 * https://leetcode-cn.com/problems/valid-anagram/
 */
public class LeetCode242 {

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < t.length(); i++) {
            arr[t.charAt(i) - 'a'] -= 1;
        }
        for (int i : arr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("anagram","nagaram"));
        System.out.println(isAnagram("rat","car"));
    }
}
