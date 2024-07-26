package com.sean.leetcode.LeetCode2740;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-07-26 08:39
 * @Description https://leetcode.cn/problems/find-the-value-of-the-partition/
 * 2740. 找出分区值
 * 给你一个 正 整数数组 nums 。
 * 将 nums 分成两个数组：nums1 和 nums2 ，并满足下述条件：
 * 数组 nums 中的每个元素都属于数组 nums1 或数组 nums2 。
 * 两个数组都 非空 。
 * 分区值 最小 。
 * 分区值的计算方法是 |max(nums1) - min(nums2)| 。
 * 其中，max(nums1) 表示数组 nums1 中的最大元素，min(nums2) 表示数组 nums2 中的最小元素。
 * 返回表示分区值的整数。
 */
public class Solution {

    public int findValueOfPartition(int[] nums) {
        Arrays.sort(nums);
        int res = Integer.MAX_VALUE;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            res = Math.min(res, nums[i] - nums[i - 1]);
        }
        return res;
    }

}
