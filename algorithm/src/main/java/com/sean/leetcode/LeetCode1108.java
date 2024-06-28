package com.sean.leetcode;

public class LeetCode1108 {

    public static String defangIPaddr(String address) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < address.length(); i++) {
            char c = address.charAt(i);
            if (c == '.') {
                builder.append("[.]");
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }

}


