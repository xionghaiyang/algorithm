package com.sean.leetcode;

/**
 * 解压缩编码列表
 * https://leetcode-cn.com/problems/decompress-run-length-encoded-list/
 */
public class LeetCode1313 {

    public static int[] decompressRLElist(int[] nums) {
        int len = 0;
        for (int i = 0; i < nums.length; i += 2) {
            len += nums[i];
        }
        int[] res = new int[len];
        int start = 0, end = 0;
        for (int i = 1; i < nums.length; i += 2) {
            end = start + nums[i - 1];
            while (start < end) {
                res[start] = nums[i];
                start++;
            }
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
        printArr(decompressRLElist(new int[]{1, 2, 3, 4}));
        printArr(decompressRLElist(new int[]{1, 1, 2, 3}));
    }

}
