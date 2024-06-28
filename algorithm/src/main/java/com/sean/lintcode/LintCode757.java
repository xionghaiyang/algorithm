package com.sean.lintcode;

public class LintCode757 {

    public int shortestUnorderedArray(int[] arr) {
        if (arr == null || arr.length <= 2) {
            return 0;
        }
        int n = arr.length;
        boolean flag = arr[1] >= arr[0];
        for (int i = 2; i < n; i++) {
            if ((arr[i] >= arr[i - 1]) ^ flag) {
                return 3;
            }
        }
        return 0;
    }

}
