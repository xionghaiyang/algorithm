package com.sean.leetcode;

/**
 * 有序数组中出现次数超过25%的元素
 * https://leetcode-cn.com/problems/element-appearing-more-than-25-in-sorted-array/
 */
public class LeetCode1287 {

    public static int findSpecialInteger(int[] arr) {
        int count = 0, res = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == res) {
                count++;
                if (count * 4 > arr.length) {
                    return res;
                }
            } else {
                res = arr[i];
                count = 1;
            }
        }
        return -1;
    }

}
