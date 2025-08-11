package com.sean.leetcode.LeetCode3644;

/**
 * @Author xionghaiyang
 * @Date 2025-08-11 18:41
 * @Description https://leetcode.cn/problems/maximum-k-to-sort-a-permutation
 * 3644. 排序排列
 * 给你一个长度为 n 的整数数组 nums，其中 nums 是范围 [0..n - 1] 内所有数字的一个 排列 。
 * 你可以在满足条件 nums[i] AND nums[j] == k 的情况下交换下标 i 和 j 的元素，其中 AND 表示按位与操作，k 是一个非负整数。
 * 返回可以使数组按 非递减 顺序排序的最大值 k（允许进行任意次这样的交换）。
 * 如果 nums 已经是有序的，返回 0。
 * 排列 是数组所有元素的一种重新排列。
 * 1 <= n == nums.length <= 10^5
 * 0 <= nums[i] <= n - 1
 * nums 是从 0 到 n - 1 的一个排列。
 */
public class Solution {

    public int sortPermutation(int[] nums) {
        int n = nums.length;
        //二进制全为1
        int res = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                res &= nums[i];
            }
        }
        return Math.max(res, 0);
    }

}
