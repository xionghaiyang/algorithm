package com.sean.nowcoder;

public class NowCoderBM94 {

    public static long maxWater(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        long res = 0;
        int left = 0;
        int right = arr.length - 1;
        int maxL = 0;
        int maxR = 0;
        while (left < right) {
            maxL = Math.max(maxL, arr[left]);
            maxR = Math.max(maxR, arr[right]);
            if (maxR > maxL) {
                res += maxL - arr[left++];
            } else {
                res += maxR - arr[right--];
            }
        }
        return res;
    }

}
