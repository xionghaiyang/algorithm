package com.sean.base.chapter03;

/**
 * @Author xionghaiyang
 * @Date 2022-08-28 12:17
 * @Description 用递归求数组的最大值
 */
public class Code08_GetMax {

    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }

}
