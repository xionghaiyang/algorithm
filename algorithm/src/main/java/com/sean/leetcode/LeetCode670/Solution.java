package com.sean.leetcode.LeetCode670;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-13 08:35
 * @Description: https://leetcode.cn/problems/maximum-swap/
 * 670. 最大交换
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 */
public class Solution {

    public int maximumSwap(int num) {
        char[] arr = String.valueOf(num).toCharArray();
        int n = arr.length;
        int begin = -1;
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i]) {
                begin = i;
                break;
            }
        }
        if (begin == -1) {
            return num;
        }
        int change = begin;
        for (int i = begin + 1; i < n; i++) {
            if (arr[i] >= arr[change]) {
                change = i;
            }
        }
        int left = begin;
        for (int i = begin - 1; i >= 0; i--) {
            if (arr[i] < arr[change]) {
                left = i;
            }
        }
        swap(arr, left, change);
        return Integer.parseInt(String.valueOf(arr));
    }

    private void swap(char[] c, int i, int j) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }

}
