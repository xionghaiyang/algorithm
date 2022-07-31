package com.sean.leetcode;

/**
 * 删除有序数组中的重复项
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 */
public class LeetCode26 {

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 0;
        int index = 1;
        while (index < nums.length) {
            if (nums[index] == nums[res]) {
                index++;
            } else {
                res++;
                nums[res] = nums[index];
                index++;
            }
        }
        return res + 1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 3, 4, 4, 5};
        int res = removeDuplicates(nums);
        for (int i = 0; i < res; i++) {
            System.out.println(nums[i]);
        }
    }
}
