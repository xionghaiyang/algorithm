package com.sean.leetcode;

/**
 * 一维数组的动态和
 * https://leetcode-cn.com/problems/running-sum-of-1d-array/
 */
public class LeetCode1480 {

    public static int[] runningSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int[] res = new int[nums.length];
        res[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] + nums[i];
        }
        return res;
    }

    private static void printArr(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        System.out.print("[");
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println(arr[arr.length - 1] + "]");
    }

    public static void main(String[] args) {
        printArr(runningSum(new int[]{1, 2, 3, 4}));
        printArr(runningSum(new int[]{1, 1, 1, 1, 1}));
        printArr(runningSum(new int[]{3, 1, 2, 10, 1}));
    }

}
