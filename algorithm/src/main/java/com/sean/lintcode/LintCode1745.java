package com.sean.lintcode;

public class LintCode1745 {

    public static boolean isMonotonic(int[] arr) {
        if (arr == null || arr.length < 2) {
            return true;
        }
        boolean flag = true;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                flag = false;
                break;
            }
        }
        if (flag) {
            return true;
        }
        flag = true;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < arr[i]) {
                flag = false;
                break;
            }
        }
        return flag;
    }

}
