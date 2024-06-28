package com.sean.nowcoder;

public class NowCoderBM91 {

    public static String solve(String str) {
        char[] s = str.toCharArray();
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            char c = s[left];
            s[left] = s[right];
            s[right] = c;
            left++;
            right--;
        }
        return new String(s);
    }

}
