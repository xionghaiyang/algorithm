package com.sean.leetcode;

public class LeetCode14 {

    public static String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String s = strs[0];
        for (int i = 0; i < strs.length; i++) {
            while (!strs[i].startsWith(s)) {
                if (s.length() == 0) {
                    return "";
                }
                s = s.substring(0, s.length() - 1);
            }
        }
        return s;
    }

    public static String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int min = 200;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < min) {
                min = strs[i].length();
            }
        }
        for (int i = min; i >= 0; i--) {
            String str = strs[0].substring(0, i);
            int count = 0;
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].startsWith(str)) {
                    count++;
                } else {
                    break;
                }
            }
            if (count == strs.length) {
                return str;
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String[] strs = {"aaa1", "aa2", "aa1asdas", "aaasdasd"};
        System.out.println(longestCommonPrefix2(strs));
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    private String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

}
