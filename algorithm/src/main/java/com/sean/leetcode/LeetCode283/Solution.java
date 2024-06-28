package com.sean.leetcode.LeetCode283;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-30 10:02
 * @Description: https://leetcode.cn/problems/move-zeroes/?envType=study-plan-v2&envId=leetcode-75
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 */
public class Solution {

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int n = nums.length;
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }
        for (int i = index; i < n; i++) {
            nums[i] = 0;
        }
    }

}
