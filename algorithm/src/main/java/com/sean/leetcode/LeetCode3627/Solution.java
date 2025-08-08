package com.sean.leetcode.LeetCode3627;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-08-08 18:03
 * @Description https://leetcode.cn/problems/maximum-median-sum-of-subsequences-of-size-3
 * 3627. 中位数之和的最大值
 * 给你一个整数数组 nums，其长度可以被 3 整除。
 * 你需要通过多次操作将数组清空。
 * 在每一步操作中，你可以从数组中选择任意三个元素，计算它们的 中位数 ，并将这三个元素从数组中移除。
 * 奇数长度数组的 中位数 定义为数组按非递减顺序排序后位于中间的元素。
 * 返回通过所有操作得到的 中位数之和的最大值 。
 * 1 <= nums.length <= 5 * 10^5
 * nums.length % 3 == 0
 * 1 <= nums[i] <= 10^9
 */
public class Solution {

    public long maximumMedianSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        long res = 0;
        for (int i = 0, j = n - 2; i < j; i++, j -= 2) {
            res += nums[j];
        }
        return res;
    }

}
