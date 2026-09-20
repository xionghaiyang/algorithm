package com.sean.leetcode.LeetCode4058;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-20 18:38
 * @Description: https://leetcode.cn/problems/maximum-pulse-value-after-one-subarray-rotation
 * 4058. 一个子数组循环移动后的最大脉冲值
 * 给你一个长度为 n 的整数数组 nums。
 * 定义整数数组 arr 的 脉冲值 为从下标 0 开始的 交替和 ：pulse(arr) = arr[0] - arr[1] + arr[2] - arr[3] + ...
 * 你可以对 nums 执行 至多一次 操作：
 * 选择两个下标 l 和 r，满足 0 <= l < r <= n - 1。
 * 将子数组 nums[l..r] 循环左移恰好 一个位置。例如，[a, b, c, d] 变为 [b, c, d, a]。
 * 返回执行 至多一次 该操作后可以获得的 最大脉冲值。
 * 子数组 是数组中连续且 非空 的元素序列。
 * 1 <= n == nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class Solution {

    public long maxValue(int[] nums) {
        int n = nums.length;
        long sum = nums[0], f0 = 0, f1 = 0, max = 0;
        for (int i = 1; i < n; i++) {
            sum += i % 2 > 0 ? -nums[i] : nums[i];
            int d = nums[i] - nums[i - 1];
            long newF = Math.max(f0, 0) + (i % 2 > 0 ? d : -d);
            f0 = f1;
            f1 = newF;
            max = Math.max(max, f1);
        }
        return sum + max * 2;
    }

}
