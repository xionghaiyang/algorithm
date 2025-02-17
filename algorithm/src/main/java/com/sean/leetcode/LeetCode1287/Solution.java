package com.sean.leetcode.LeetCode1287;

/**
 * @Author xionghaiyang
 * @Date 2025-02-17 09:45
 * @Description https://leetcode.cn/problems/element-appearing-more-than-25-in-sorted-array/
 * 1287. 有序数组中出现次数超过25%的元素
 * 给你一个非递减的 有序 整数数组，已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的 25%。
 * 请你找到并返回这个整数
 * 1 <= arr.length <= 10^4
 * 0 <= arr[i] <= 10^5
 */
public class Solution {

    public int findSpecialInteger(int[] arr) {
        int n = arr.length;
        int res = arr[0], count = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == res) {
                count++;
                if (count * 4 > n) {
                    return res;
                }
            } else {
                res = arr[i];
                count = 1;
            }
        }
        return -1;
    }

    public int findSpecialInteger1(int[] arr) {
        int n = arr.length;
        int span = n / 4 + 1;
        for (int i = 0; i < n; i += span) {
            int start = binarySearch(arr, arr[i]);
            int end = binarySearch(arr, arr[i] + 1);
            if (end - start >= span) {
                return arr[i];
            }
        }
        return -1;
    }

    private int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int res = arr.length;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= target) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

}
