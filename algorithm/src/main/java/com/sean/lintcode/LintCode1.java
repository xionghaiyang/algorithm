package com.sean.lintcode;

public class LintCode1 {

    public static int aplusb(int a, int b) {
        int ans = a;
        while (b != 0) {
            ans = a ^ b;
            b = (a & b) << 1;
            a = ans;
        }
        return ans;
    }

}
