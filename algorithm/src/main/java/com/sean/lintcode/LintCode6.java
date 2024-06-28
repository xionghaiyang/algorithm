package com.sean.lintcode;

public class LintCode6 {

    public static int[] mergeSortedArray(int[] a, int[] b) {
        if (a == null || a.length == 0) {
            return b;
        }
        if (b == null || b.length == 0) {
            return a;
        }
        int[] res = new int[a.length + b.length];
        int index = 0, indexA = 0, indexB = 0;
        while (indexA < a.length && indexB < b.length) {
            if (a[indexA] < b[indexB]) {
                res[index++] = a[indexA++];
            } else {
                res[index++] = b[indexB++];
            }
        }
        while (indexA < a.length) {
            res[index++] = a[indexA++];
        }
        while (indexB < b.length) {
            res[index++] = b[indexB++];
        }
        return res;
    }

}
