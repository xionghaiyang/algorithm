package com.sean.leetcode;

public class LeetCode829 {

    public static int consecutiveNumbersSum(int n) {
        int res = 0;
        int bound = 2 * n;
        for (int k = 1; k * (k + 1) <= bound; k++) {
            if (isKConsecutive(n, k)) {
                res++;
            }
        }
        return res;
    }

    public static boolean isKConsecutive(int n, int k) {
        if (k % 2 == 1) {
            return n % k == 0;
        } else {
            return n % k != 0 && 2 * n % k == 0;
        }
    }

}
