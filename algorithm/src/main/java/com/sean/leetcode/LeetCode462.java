package com.sean.leetcode;

import java.util.Arrays;
import java.util.Random;

public class LeetCode462 {

    public static int minMoves21(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, res = 0, mid = nums[n / 2];
        for (int i = 0; i < n; i++) {
            res += Math.abs(nums[i] - mid);
        }
        return res;
    }

    public static Random random = new Random();

    public static int minMoves2(int[] nums) {
        int n = nums.length, x = quickSelect(nums, 0, n - 1, n / 2), res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.abs(nums[i] - x);
        }
        return res;
    }

    public static int quickSelect(int[] nums, int left, int right, int index) {
        int q = randomPartition(nums, left, right);
        if (q == index) {
            return nums[q];
        } else {
            return q < index ? quickSelect(nums, q + 1, right, index) : quickSelect(nums, left, q - 1, index);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static int randomPartition(int[] nums, int left, int right) {
        int i = random.nextInt(right - left + 1) + left;
        swap(nums, i, right);
        return partition(nums, left, right);
    }

    public static int partition(int[] nums, int left, int right) {
        int x = nums[right], i = left - 1;
        for (int j = left; j < right; j++) {
            if (nums[j] <= x) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, right);
        return i + 1;
    }

}
