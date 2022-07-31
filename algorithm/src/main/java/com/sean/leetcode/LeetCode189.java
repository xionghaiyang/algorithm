package com.sean.leetcode;

/**
 * 旋转数组
 * https://leetcode-cn.com/problems/rotate-array/
 */
public class LeetCode189 {

    public static void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int n = nums.length;
        k = k % n;
        int count = gcd(k, n); //最大公约数
        for (int start = 0; start < count; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
            } while (start != current);
        }
    }

    private static int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

    private static void printArr(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        rotate(nums1, 3);
        printArr(nums1);

        int[] nums2 = {-1,-100,3,99};
        rotate(nums2, 2);
        printArr(nums2);
    }

}
