package com.sean.leetcode.LeetCodeInterview1011;

/**
 * @Author xionghaiyang
 * @Date 2026-03-20 20:31
 * @Description https://leetcode.cn/problems/peaks-and-valleys-lcci
 * 面试题 10.11. 峰与谷
 * 在一个整数数组中，“峰”是大于或等于相邻整数的元素，相应地，“谷”是小于或等于相邻整数的元素。
 * 例如，在数组{5, 8, 4, 2, 3, 4, 6}中，{8, 6}是峰， {5, 2}是谷。
 * 现在给定一个整数数组，将该数组按峰与谷的交替顺序排序。
 * nums.length <= 10000
 */
public class Solution {

    public void wiggleSort(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if ((i & 1) == 0) {
                if (nums[i] > nums[i - 1]) {
                    swap(nums, i, i - 1);
                }
            } else {
                if (nums[i] < nums[i - 1]) {
                    swap(nums, i, i - 1);
                }
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
