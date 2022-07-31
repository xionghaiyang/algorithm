package com.sean.leetcode;

import java.util.Random;

/**
 * 数组中的第K个最大元素
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/submissions/
 */
public class LeetCode215 {

    Random random = new Random();

    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    public int quickSelect(int[] arr, int left, int right, int index) {
        int q = randomPartition(arr, left, right);
        if (q == index) {
            return arr[q];
        } else {
            return q < index ? quickSelect(arr, q + 1, right, index) : quickSelect(arr, left, q - 1, index);
        }
    }

    public int randomPartition(int[] arr, int left, int right) {
        int i = random.nextInt(right - left + 1) + left;
        swap(arr, i, right);
        return partition(arr, left, right);
    }

    public int partition(int[] arr, int left, int right) {
        int x = arr[right], i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] <= x) {
                swap(arr, ++i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
