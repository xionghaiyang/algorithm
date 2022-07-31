package com.sean.lintcode;

public class LintCode1294 {

    public static boolean isPowerOfThree(int n) {
        while (n > 0 && n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

}
