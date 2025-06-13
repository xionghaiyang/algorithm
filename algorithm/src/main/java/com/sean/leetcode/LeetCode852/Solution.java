package com.sean.leetcode.LeetCode852;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-18 11:15
 * @Description: https://leetcode.cn/problems/peak-index-in-a-mountain-array/description/
 * 852. 山脉数组的峰顶索引
 * 符合下列属性的数组 arr 称为 山脉数组 ：
 * arr.length >= 3
 * 存在 i（0 < i < arr.length - 1）使得：
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * 给你由整数组成的山脉数组 arr ，
 * 返回满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。
 * 你必须设计并实现时间复杂度为 O(log(n)) 的解决方案。
 * 3 <= arr.length <= 10^5
 * 0 <= arr[i] <= 10^6
 * 题目数据 保证 arr 是一个山脉数组
 */
public class Solution {

    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int left = 1, right = n - 2, res = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] > arr[mid + 1]) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

}
