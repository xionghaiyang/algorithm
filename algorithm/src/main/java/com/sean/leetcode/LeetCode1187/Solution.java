package com.sean.leetcode.LeetCode1187;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2023-04-20 08:22
 * @Description: https://leetcode.cn/problems/make-array-strictly-increasing
 * 1187. 使数组严格递增
 * 给你两个整数数组 arr1 和 arr2，返回使 arr1 严格递增所需要的最小「操作」数（可能为 0）。
 * 每一步「操作」中，你可以分别从 arr1 和 arr2 中各选出一个索引，
 * 分别为 i 和 j，0 <= i < arr1.length 和 0 <= j < arr2.length，然后进行赋值运算 arr1[i] = arr2[j]。
 * 如果无法让 arr1 严格递增，请返回 -1。
 * 1 <= arr1.length, arr2.length <= 2000
 * 0 <= arr1[i], arr2[i] <= 10^9
 */
public class Solution {

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        int m = arr1.length;
        Arrays.sort(arr2);
        int n = 0;
        for (int i = 1; i < arr2.length; i++) {
            if (arr2[n] != arr2[i]) {
                arr2[++n] = arr2[i];
            }
        }
        int[] memo = new int[m + 1];
        int res = dfs(arr1, arr2, n, memo, m);
        return res < 0 ? -1 : m + 1 - res;
    }

    private int dfs(int[] arr1, int[] arr2, int n, int[] memo, int i) {
        if (memo[i] != 0) {
            return memo[i];
        }
        int x = i < arr1.length ? arr1[i] : Integer.MAX_VALUE;
        int k = binarySearch(arr2, n, x);
        //小于arr[i]的数全部替换
        int res = k < i ? Integer.MIN_VALUE : 0;
        //无替换
        if (i > 0 && arr1[i - 1] < x) {
            res = Math.max(res, dfs(arr1, arr2, n, memo, i - 1));
        }
        for (int j = i - 2; j >= i - k - 1 && j >= 0; j--) {
            if (arr2[k - (i - j - 1)] > arr1[j]) {
                res = Math.max(res, dfs(arr1, arr2, n, memo, j));
            }
        }
        return memo[i] = ++res;
    }

    private int binarySearch(int[] nums, int right, int target) {
        int left = 0, res = right + 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] >= target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

}
