package com.sean.leetcode;

/**
 * 加一
 * https://leetcode-cn.com/problems/plus-one/
 */
public class LeetCode66 {

    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i] += 1;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    private static void printArr(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            System.out.print(arr[i]);
        }
        System.out.println(arr[n - 1]);
    }

    public static void main(String[] args) {
        printArr(plusOne(new int[]{1, 2, 3}));
        printArr(plusOne(new int[]{4,3,2,1}));
        printArr(plusOne(new int[]{0}));
    }
}
