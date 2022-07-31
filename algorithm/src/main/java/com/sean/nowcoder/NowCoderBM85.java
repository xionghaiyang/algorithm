package com.sean.nowcoder;

public class NowCoderBM85 {

    public static String solve(String IP) {
        if (isIPv4(IP)) {
            return "IPv4";
        } else if (isIPv6(IP)) {
            return "IPv6";
        } else {
            return "Neither";
        }
    }

    public static boolean isIPv4(String IP) {
        if (IP.indexOf('.') == -1) {
            return false;
        }
        String[] s = IP.split("\\.");
        if (s.length != 4) {
            return false;
        }
        for (int i = 0; i < s.length; i++) {
            if (s[i].length() == 0 || s[i].length() > 3 || (s[i].charAt(0) == '0' && s[i].length() != 1)) {
                return false;
            }
            int num = 0;
            for (int j = 0; j < s[i].length(); j++) {
                char c = s[i].charAt(j);
                if (c < '0' || c > '9') {
                    return false;
                }
                num = num * 10 + (int) (c - '0');
                if (num > 255) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isIPv6(String IP) {
        if (IP.indexOf(':') == -1) {
            return false;
        }
        String[] s = IP.split(":", -1);
        if (s.length != 8) {
            return false;
        }
        for (int i = 0; i < s.length; i++) {
            if (s[i].length() == 0 || s[i].length() > 4) {
                return false;
            }
            for (int j = 0; j < s[i].length(); j++) {
                char c = s[i].charAt(j);
                boolean expr = (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
                if (!expr) {
                    return false;
                }
            }
        }
        return true;
    }

}
