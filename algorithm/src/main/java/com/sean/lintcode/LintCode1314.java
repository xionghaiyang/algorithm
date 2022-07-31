package com.sean.lintcode;

public class LintCode1314 {

    public static boolean isPowerOfTwo1(int n) {
        int sum = 0;
        while (n != 0) {
            if ((n & 1) != 0) {
                sum++;
            }
            n >>= 1;
        }
        return sum == 1;
    }

    public static boolean isPowerOfTwo(int n) {
        if (n < 0) {
            return false;
        }
        return (n & (n - 1)) == 0;
    }

}
