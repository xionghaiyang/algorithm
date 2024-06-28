package com.sean.nowcoder;

public class NowCoderBM97 {

    public static int[] solve(int n, int m, int[] a) {
        m = m % n;
        reverse(a, 0, n - 1);
        reverse(a, 0, m - 1);
        reverse(a, m, n - 1);
        return a;
    }

    public static void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left++, right--);
        }
    }

    public static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

}
