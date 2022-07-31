package com.sean.leetcode;

/**
 * 搜索插入位置
 * https://leetcode-cn.com/problems/search-insert-position/
 */
public class LeetCode35 {

    public static int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 8, 9};
        int target = 7;
        System.out.println(searchInsert(nums,target));
    }

}
