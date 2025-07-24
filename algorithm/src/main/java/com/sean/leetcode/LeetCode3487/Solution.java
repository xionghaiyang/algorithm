package com.sean.leetcode.LeetCode3487;

/**
 * @Author xionghaiyang
 * @Date 2025-07-25 05:51
 * @Description https://leetcode.cn/problems/maximum-unique-subarray-sum-after-deletion
 * 3487. 删除后的最大子数组元素和
 * 给你一个整数数组 nums 。
 * 你可以从数组 nums 中删除任意数量的元素，但不能将其变为 空 数组。
 * 执行删除操作后，选出 nums 中满足下述条件的一个子数组：
 * 子数组中的所有元素 互不相同 。
 * 最大化 子数组的元素和。
 * 返回子数组的 最大元素和 。
 * 子数组 是数组的一个连续、非空 的元素序列。
 * 1 <= nums.length <= 100
 * -100 <= nums[i] <= 100
 */
public class Solution {

    public int maxSum(int[] nums) {
        boolean[] set = new boolean[201];
        int sum = 0, max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > 0 && !set[num + 100]) {
                set[num + 100] = true;
                sum += num;
            }
            max = Math.max(max, num);
        }
        return sum > 0 ? sum : max;
    }

}
