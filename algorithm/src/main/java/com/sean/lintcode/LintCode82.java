package com.sean.lintcode;

public class LintCode82 {

    public static int singleNumber(int[] a) {
        if (a == null || a.length == 0) {
            return -1;
        }
        int res = 0;
        for (int num : a) {
            res ^= num;
        }
        return res;
    }

}
