package com.sean.nowcoder;

public class NowCoderBM20 {

    public static int mod = 1000000007;

    public static int merge(int[] array, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = help.length - 1;
        int p1 = mid;
        int p2 = right;
        int res = 0;
        while (p1 >= left && p2 >= mid + 1) {
            res += array[p1] > array[p2] ? (p2 - mid) : 0;
            res %= mod;
            help[i--] = array[p1] > array[p2] ? array[p1--] : array[p2--];
        }
        while (p1 >= left) {
            help[i--] = array[p1--];
        }
        while (p2 >= mid + 1) {
            help[i--] = array[p2--];
        }
        for (i = 0; i < help.length; i++) {
            array[left + i] = help[i];
        }
        return res;
    }

    public static int process(int[] array, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return ((process(array, left, mid) + process(array, mid + 1, right)) % mod + merge(array, left, mid, right)) % mod;
    }

    public static int InversePairs(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        return process(array, 0, array.length - 1);
    }

}
