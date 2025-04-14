package com.sean.course02.lesson03;

/**
 * @Author xionghaiyang
 * @Date 2025-04-14 13:49
 * @Description 用递归方法求数组的最大值
 */
public class Code08_GetMax {

    public int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    private int process(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }
        int mid = left + ((right - left) >> 1);
        int leftMax = process(arr, left, mid);
        int rightMax = process(arr, mid + 1, right);
        return Math.max(leftMax, rightMax);
    }

}
