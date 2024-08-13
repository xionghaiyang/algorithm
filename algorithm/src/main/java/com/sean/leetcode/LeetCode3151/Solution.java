package com.sean.leetcode.LeetCode3151;

/**
 * @Author xionghaiyang
 * @Date 2024-08-13 08:34
 * @Description https://leetcode.cn/problems/special-array-i/
 * 3151. 特殊数组 I
 * 如果数组的每一对相邻元素都是两个奇偶性不同的数字，则该数组被认为是一个 特殊数组 。
 * Aging 有一个整数数组 nums。
 * 如果 nums 是一个 特殊数组 ，返回 true，否则返回 false。
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 */
public class Solution {

    public boolean isArraySpecial(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if ((nums[i - 1] + nums[i]) % 2 != 1) {
                return false;
            }
        }
        return true;
    }

}
