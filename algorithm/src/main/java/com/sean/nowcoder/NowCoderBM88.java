package com.sean.nowcoder;

public class NowCoderBM88 {

    public static boolean judge(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        int left = 0;
        int right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left) == str.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

}
