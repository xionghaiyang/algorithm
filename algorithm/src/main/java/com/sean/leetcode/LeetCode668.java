package com.sean.leetcode;

public class LeetCode668 {

    public static int findKthNumber(int m, int n, int k) {
        int left = 1, right = m * n;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            int count = 0;
            for (int i = 1; i <= m; i++) {
                count += Math.min(n, mid / i);
            }
            if (count >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

}
