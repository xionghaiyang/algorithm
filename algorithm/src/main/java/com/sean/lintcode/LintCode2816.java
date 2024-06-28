package com.sean.lintcode;

public class LintCode2816 {

    public static void main(String[] args) {
        int res = 4;
        while (true) {
            if (res % 3 == 2 && res % 5 == 3) {
                System.out.println(res);
                break;
            }
            res += 7;
        }
    }

}
