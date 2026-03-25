package com.sean.leetcode.LeetCodeInterview1714;

/**
 * @Author xionghaiyang
 * @Date 2026-03-25 18:50
 * @Description https://leetcode.cn/problems/smallest-k-lcci
 * 面试题 17.14. 最小K个数
 * 设计一个算法，找出数组中最小的k个数。
 * 以任意顺序返回这k个数均可。
 * 0 <= len(arr) <= 100000
 * 0 <= k <= min(100000, len(arr))
 */
public class Solution {

    public int[] smallestK(int[] arr, int k) {
        quickSort(arr, k, 0, arr.length - 1);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    private void quickSort(int[] arr, int k, int left, int right) {
        if (left >= right) {
            return;
        }
        swap(arr, left + (int) (Math.random() * (right - left + 1)), right);
        int[] equalArea = partition(arr, left, right);
        if (equalArea[0] > k - 1) {
            quickSort(arr, k, left, equalArea[0] - 1);
        } else if (equalArea[1] < k - 1) {
            quickSort(arr, k, equalArea[1] + 1, right);
        }
    }

    private int[] partition(int[] arr, int left, int right) {
        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, right};
        }
        int less = left - 1, more = right, index = left;
        while (index < more) {
            if (arr[index] == arr[right]) {
                index++;
            } else if (arr[index] < arr[right]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, more, right);
        return new int[]{less + 1, more};
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
