package com.sean.leetcode;

/**
 * 寻找旋转排序数组中的最小值 II
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
 */
public class LeetCode154 {

    public static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1, mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                right--;
            }
        }
        return nums[left];
    }

    public static int findMin1(int[] nums) {
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[index] > nums[i]) {
                index = i;
                break;
            }
        }
        return nums[index];
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{1, 3, 5}));
        System.out.println(findMin(new int[]{2, 2, 2, 0, 1}));

        System.out.println(findMin1(new int[]{1, 3, 5}));
        System.out.println(findMin1(new int[]{2, 2, 2, 0, 1}));
    }
}
