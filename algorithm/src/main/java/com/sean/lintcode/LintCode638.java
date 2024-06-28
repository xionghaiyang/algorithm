package com.sean.lintcode;

public class LintCode638 {

    public static boolean isIsomorphic(String s, String t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null ^ t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        int[] S = new int[256];
        int[] T = new int[256];
        for (int i = 0; i < s.length(); i++) {
            if (S[s.charAt(i)] != T[t.charAt(i)]) {
                return false;
            }
            S[s.charAt(i)] = i;
            T[t.charAt(i)] = i;
        }
        return true;
    }

}
