package com.sean.lintcode;

public class LintCode1350 {

    public static String convertToTitle(int n) {
        StringBuilder builder = new StringBuilder();
        while (n != 0) {
            n--;
            builder.append((char) (n % 26 + 'A'));
            n = n / 26;
        }
        return builder.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToTitle(27));
    }

}
