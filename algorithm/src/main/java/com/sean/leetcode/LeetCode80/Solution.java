package com.sean.leetcode.LeetCode80;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-13 13:49
 * @Description: https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/?envType=study-plan-v2&envId=top-interview-150
 * 80. 删除有序数组中的重复项 II
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 1 <= nums.length <= 3 * 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums 已按升序排列
 */
public class Solution {

    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int size = 2;
        for (int i = 2; i < n; i++) {
            if (nums[i] != nums[size - 2]) {
                nums[size++] = nums[i];
            }
        }
        return Math.min(size, n);
    }

}
