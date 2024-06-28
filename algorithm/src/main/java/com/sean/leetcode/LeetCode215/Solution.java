package com.sean.leetcode.LeetCode215;

import java.util.Random;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-02 17:55
 * @Description: https://leetcode.cn/problems/kth-largest-element-in-an-array/description/
 * 215. 数组中的第K个最大元素
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 */
public class Solution {

    Random random = new Random();

    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return quickSelect(nums, 0, n - 1, n - k);
    }

    private int quickSelect(int[] arr, int left, int right, int index) {
        int q = randomPartition(arr, left, right);
        if (q == index) {
            return arr[q];
        } else {
            return q < index ? quickSelect(arr, q + 1, right, index) : quickSelect(arr, left, q - 1, index);
        }
    }

    private int randomPartition(int[] arr, int left, int right) {
        int i = random.nextInt(right - left + 1) + left;
        swap(arr, i, right);
        return partition(arr, left, right);
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private int partition(int[] arr, int left, int right) {
        int x = arr[right], i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] <= x) {
                swap(arr, ++i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }


    public int findKthLargest1(int[] arr, int k) {
        int n = arr.length;
        return quickSelect1(arr, 0, n - 1, n - k);
    }

    private int quickSelect1(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[k];
        }
        int x = arr[left], i = left - 1, j = right + 1;
        while (i < j) {
            do {
                i++;
            } while (arr[i] < x);
            do {
                j--;
            } while (arr[j] > x);
            if (i < j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        if (k <= j) {
            return quickSelect1(arr, left, j, k);
        } else {
            return quickSelect1(arr, j + 1, right, k);
        }
    }

}
