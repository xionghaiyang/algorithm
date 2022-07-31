package com.sean.leetcode;


import java.util.Arrays;

public class LeetCode2408 {

    public String handle(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

}
