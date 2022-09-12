package com.sean.leetcode.LeetCode1608;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2022-09-12 20:32
 * @Description https://leetcode.cn/problems/special-array-with-x-elements-greater-than-or-equal-x/
 * 1608. 特殊数组的特征值
 * 给你一个非负整数数组 nums 。如果存在一个数 x ，使得 nums 中恰好有 x 个元素 大于或者等于 x ，那么就称 nums 是一个 特殊数组 ，而 x 是该数组的 特征值 。
 * 注意： x 不必 是 nums 的中的元素。
 * 如果数组 nums 是一个 特殊数组 ，请返回它的特征值 x 。否则，返回 -1 。可以证明的是，如果 nums 是特殊数组，那么其特征值 x 是 唯一的 。
 */
public class Solution {

    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        if (nums[0] >= n) {
            return n;
        }
        for (int i = 1; i < n; i++) {
            if (nums[n - i] >= i && nums[n - i - 1] < i) {
                return i;
            }
        }
        return -1;
    }

}
