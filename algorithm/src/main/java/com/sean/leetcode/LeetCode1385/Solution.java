package com.sean.leetcode.LeetCode1385;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-05 06:21
 * @Description: https://leetcode.cn/problems/find-the-distance-value-between-two-arrays
 * 1385. 两个数组间的距离值
 * 给你两个整数数组 arr1 ， arr2 和一个整数 d ，请你返回两个数组之间的 距离值 。
 * 「距离值」 定义为符合此距离要求的元素数目：对于元素 arr1[i] ，不存在任何元素 arr2[j] 满足 |arr1[i]-arr2[j]| <= d 。
 * 1 <= arr1.length, arr2.length <= 500
 * -10^3 <= arr1[i], arr2[j] <= 10^3
 * 0 <= d <= 100
 */
public class Solution {

    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int n = arr2.length;
        Arrays.sort(arr2);
        int res = 0;
        for (int num : arr1) {
            int index = binarySearch(arr2, num - d);
            if (index == n || arr2[index] > num + d) {
                res++;
            }
        }
        return res;
    }

    //返回大于等于target的数的最小索引,未找到返回arr的长度
    private int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1, res = arr.length;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] >= target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

}
