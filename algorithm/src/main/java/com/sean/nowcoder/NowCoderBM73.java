package com.sean.nowcoder;

public class NowCoderBM73 {

    public static int process(String s, int begin, int end) {
        while (begin >= 0 && end < s.length() && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }
        return end - begin - 1;
    }

    public static int getLongestPalindrome(String A) {
        int res = 1;
        for (int i = 0; i < A.length() - 1; i++) {
            res = Math.max(res, Math.max(process(A, i, i), process(A, i, i + 1)));
        }
        return res;
    }

}
