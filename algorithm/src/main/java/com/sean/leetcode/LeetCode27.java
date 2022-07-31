package com.sean.leetcode;

/**
 * 移除元素
 * https://leetcode-cn.com/problems/remove-element/
 */
public class LeetCode27 {

    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[res] = nums[i];
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 2, 3, 2, 5};
        int val = 2;
        int res = removeElement(nums, val);
        for (int i = 0; i < res; i++) {
            System.out.println(nums[i]);
        }
    }

}
