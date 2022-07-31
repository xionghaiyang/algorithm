package com.sean.leetcode;

public class LeetCode12 {

    public String intToRoman(int num) {
        int[] keys = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] values = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            int key = keys[i];
            String value = values[i];
            while (num >= key) {
                num -= key;
                res.append(value);
            }
            if (num == 0) {
                break;
            }
        }
        return res.toString();
    }

}
