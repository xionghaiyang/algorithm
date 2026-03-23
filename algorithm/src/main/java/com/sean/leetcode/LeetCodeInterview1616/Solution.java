package com.sean.leetcode.LeetCodeInterview1616;

/**
 * @Author xionghaiyang
 * @Date 2026-03-23 21:21
 * @Description https://leetcode.cn/problems/sub-sort-lcci
 * 面试题 16.16. 部分排序
 * 给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。
 * 注意：n-m尽量最小，也就是说，找出符合条件的最短序列。
 * 函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
 * 0 <= len(array) <= 1000000
 */
public class Solution {

    public int[] subSort(int[] array) {
        int n = array.length;
        if (n == 0) {
            return new int[]{-1, -1};
        }
        int start = n - 1, end = 0, rMin = Integer.MAX_VALUE, lMax = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (array[i] <= rMin) {
                rMin = array[i];
            } else {
                start = i;
            }
        }
        for (int i = 0; i < n; i++) {
            if (array[i] >= lMax) {
                lMax = array[i];
            } else {
                end = i;
            }
        }
        if (start < end) {
            return new int[]{start, end};
        } else {
            return new int[]{-1, -1};
        }
    }

}
