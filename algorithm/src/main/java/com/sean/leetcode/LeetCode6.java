package com.sean.leetcode;

public class LeetCode6 {

    public String convert1(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuffer res = new StringBuffer();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                res.append(s.charAt(i + j));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) {
                    res.append(s.charAt(j + cycleLen - i));
                }
            }
        }
        return res.toString();
    }

    public String convert(String s, int numRows) {
        int length = s.length();
        if (numRows == 1 || numRows >= length) {
            return s;
        }
        StringBuffer[] mat = new StringBuffer[numRows];
        for (int i = 0; i < numRows; i++) {
            mat[i] = new StringBuffer();
        }
        int k = 2 * numRows - 2;
        for (int i = 0, j = 0; i < length; i++) {
            mat[j].append(s.charAt(i));
            if (i % k < numRows - 1) {
                j++;
            } else {
                j--;
            }
        }
        StringBuffer res = new StringBuffer();
        for (StringBuffer row : mat) {
            res.append(row);
        }
        return res.toString();
    }

}
