package com.sean.leetcode;

public class LeetCode556 {

    public static int nextGreaterElement(int n) {
        char[] nums = Integer.toString(n).toCharArray();
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i < 0) {
            return -1;
        }
        int j = nums.length - 1;
        while (j >= 0 && nums[i] >= nums[j]) {
            j--;
        }
        swap(nums, i, j);
        reverse(nums, i + 1);
        long res = Long.parseLong(new String(nums));
        return res > Integer.MAX_VALUE ? -1 : (int) res;
    }

    private static void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void reverse(char[] nums, int begin) {
        int i = begin, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

}
