package com.sean.leetcode;

/**
 * 有多少小于当前数字的数字
 * https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number/
 */
public class LeetCode1365 {

    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] arr = new int[101];
        for (int num : nums) {
            arr[num]++;
        }
        int[] preSum = new int[arr.length];
        preSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }
        int[] res = new int[nums.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = preSum[nums[i]] - arr[nums[i]];
        }
        return res;
    }

    private static void printArr(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                System.out.print(arr[i] + ",");
            } else {
                System.out.print(arr[i]);
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        printArr(smallerNumbersThanCurrent(new int[]{8, 1, 2, 2, 3}));
        printArr(smallerNumbersThanCurrent(new int[]{6, 5, 4, 8}));
        printArr(smallerNumbersThanCurrent(new int[]{7, 7, 7, 7}));
    }
}
