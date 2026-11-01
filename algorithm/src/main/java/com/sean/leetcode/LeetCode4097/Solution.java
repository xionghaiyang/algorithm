package com.sean.leetcode.LeetCode4097;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-01 17:37
 * @Description: https://leetcode.cn/problems/minimum-value-of-merged-integers
 * 4097. 合并整数后的最小值
 * 给你一个长度为偶数 n 的整数数组 nums。
 * 你需要将 nums 中的元素两两合并：将 nums[0] 与 nums[1] 合并，将 nums[2] 与 nums[3] 合并，依此类推，直到将 nums[n - 2] 与 nums[n - 1] 合并。
 * 返回所有合并后得到的整数中的最小值。
 * 合并两个整数，是指将第二个整数的所有十进制数位拼接在第一个整数的所有十进制数位之后。
 * 2 <= n == nums.length <= 100
 * n 为偶数
 * 1 <= nums[i] <= 1000
 */
public class Solution {

    public int minMergedInteger(int[] nums) {
        int n = nums.length, res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i += 2) {
            int x = nums[i], y = nums[i + 1];
            for (int v = y; v > 0; v /= 10) {
                x *= 10;
            }
            res = Math.min(res, x + y);
        }
        return res;
    }

}
