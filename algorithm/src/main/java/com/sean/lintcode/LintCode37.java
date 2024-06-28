package com.sean.lintcode;

public class LintCode37 {

    public int reverseInteger(int number) {
        int res = 0;
        while (number != 0) {
            res = res * 10 + (number % 10);
            number /= 10;
        }
        return res;
    }

}
