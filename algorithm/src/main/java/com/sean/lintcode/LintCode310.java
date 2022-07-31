package com.sean.lintcode;

public class LintCode310 {

    public static String distortion(String a) {
        if (a == null || a.length() < 2) {
            return a;
        }
        int left = 0, right = a.length() - 1;
        StringBuilder stringBuilder = new StringBuilder();
        while (left < right) {
            stringBuilder.append(a.charAt(right--));
            stringBuilder.append(a.charAt(left++));
        }
        if (left == right) {
            stringBuilder.append(a.charAt(left));
        }
        return stringBuilder.toString();
    }

}
