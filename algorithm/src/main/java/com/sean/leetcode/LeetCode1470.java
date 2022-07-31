package com.sean.leetcode;

/**
 * 重新排列数组
 * https://leetcode-cn.com/problems/shuffle-the-array/
 */
public class LeetCode1470 {

    public static int[] shuffle(int[] nums, int n) {
        if (nums == null || nums.length != 2 * n) {
            return null;
        }
        int[] res = new int[nums.length];
        for (int i = 0; i < n; i++) {
            res[2 * i] = nums[i];
            res[2 * i + 1] = nums[n + i];
        }
        return res;
    }

    private static void printArr(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
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
        printArr(shuffle(new int[]{2, 5, 1, 3, 4, 7},3));
        printArr(shuffle(new int[]{1, 2, 3, 4, 4, 3, 2, 1},4));
        printArr(shuffle(new int[]{1, 1, 2, 2},2));
    }

}
