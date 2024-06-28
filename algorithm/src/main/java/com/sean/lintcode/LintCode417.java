package com.sean.lintcode;

public class LintCode417 {

    public static boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        s = s.trim();
        if (s.length() == 0) {
            return false;
        }
        int n = s.length();
        int i = 0;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            i++;
        }
        if (i == n) {
            return false;
        }
        if (Character.isDigit(s.charAt(i))) {
            i++;
        }
        boolean dot = false;
        boolean e = false;
        while (i < n) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                i++;
            } else if (c == 'e') {
                if (i == n - 1) {
                    return false;
                }
                if (e || i == 0 || !(Character.isDigit(s.charAt(i - 1)) || (i > 1 && s.charAt(i - 1) == '.'))) {
                    return false;
                }
                e = true;
                i++;
            } else if (c == '-' || c == '+') {
                if (i == n - 1) {
                    return false;
                }
                if (s.charAt(i - 1) != 'e') {
                    return false;
                }
                i++;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    i++;
                }
                return i == n;
            } else if (c == '.') {
                if (e || dot || (i != 0 && !(Character.isDigit(s.charAt(i - 1)) || (s.charAt(i - 1) == '-' || s.charAt(i - 1) == '+')))) {
                    return false;
                }
                if (i == n - 1 && (i == 0 || !Character.isDigit(s.charAt(i - 1)))) {
                    return false;
                }
                dot = true;
                i++;
            } else {
                return false;
            }
        }
        return i == n;
    }

}
