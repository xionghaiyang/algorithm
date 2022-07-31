package com.sean.lintcode;

public class LintCode765 {

    public static boolean isValidTriangle(int a, int b, int c) {
        return (a + b > c) && (b + c > a) && (c + a > b);
    }

}
