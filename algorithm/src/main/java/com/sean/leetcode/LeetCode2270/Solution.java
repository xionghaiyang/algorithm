package com.sean.leetcode.LeetCode2270;

/**
 * @Author xionghaiyang
 * @Date 2025-01-13 22:54
 * @Description https://leetcode.cn/problems/number-of-ways-to-split-array/
 * 2270. 分割数组的方案数
 * 给你一个下标从 0 开始长度为 n 的整数数组 nums 。
 * 如果以下描述为真，那么 nums 在下标 i 处有一个 合法的分割 ：
 * 前 i + 1 个元素的和 大于等于 剩下的 n - i - 1 个元素的和。
 * 下标 i 的右边 至少有一个 元素，也就是说下标 i 满足 0 <= i < n - 1 。
 * 请你返回 nums 中的 合法分割 方案数。
 * 2 <= nums.length <= 10^5
 * -105 <= nums[i] <= 10^5
 */
public class Solution {

    public int waysToSplitArray(int[] nums) {
        int n = nums.length;
        long left = 0, right = 0;
        for (int num : nums) {
            right += num;
        }
        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            left += nums[i];
            right -= nums[i];
            if (left >= right) {
                res++;
            }
        }
        return res;
    }

}
