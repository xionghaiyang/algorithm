package com.sean.leetcode.LeetCode3689;

/**
 * @Author xionghaiyang
 * @Date 2025-09-22 20:04
 * @Description https://leetcode.cn/problems/maximum-total-subarray-value-i
 * 3689. 最大子数组总值 I
 * 给定一个长度为 n 的整数数组 nums 和一个整数 k。
 * 你必须从 nums 中选择 恰好 k 个非空子数组 nums[l..r]。
 * 子数组可以重叠，同一个子数组（相同的 l 和 r）可以 被选择超过一次。
 * 子数组 nums[l..r] 的 值 定义为：max(nums[l..r]) - min(nums[l..r])。
 * 总值 是所有被选子数组的 值 之和。
 * 返回你能实现的 最大 可能总值。
 * 子数组 是数组中连续的 非空 元素序列。
 * 1 <= n == nums.length <= 5 * 10^4
 * 0 <= nums[i] <= 10^9
 * 1 <= k <= 10^5
 */
public class Solution {

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        int min = nums[0], max = nums[0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        return (long) (max - min) * k;
    }

}
