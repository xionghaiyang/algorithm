package com.sean.leetcode.LeetCode1053;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-03 08:12
 * @Description: https://leetcode.cn/problems/previous-permutation-with-one-swap/
 * 1053. 交换一次的先前排列
 * 给你一个正整数数组 arr（可能存在重复的元素），
 * 请你返回可在 一次交换（交换两数字 arr[i] 和 arr[j] 的位置）后得到的、按字典序排列小于 arr 的最大排列。
 * 如果无法这么操作，就请返回原数组。
 */
public class Solution {

    public int[] prevPermOpt1(int[] arr) {
        int n = arr.length;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                int j = n - 1;
                while (arr[j] >= arr[i] || arr[j] == arr[j - 1]) {
                    j--;
                }
                swap(arr, i, j);
                break;
            }
        }
        return arr;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
