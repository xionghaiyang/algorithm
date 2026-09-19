package com.sean.leetcode.LeetCode3840;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-19 18:24
 * @Description: https://leetcode.cn/problems/house-robber-v
 * 3840. 打家劫舍 V
 * 你是一名专业小偷，计划偷窃沿街的房屋。
 * 每间房屋都藏有一定的现金，并由带有颜色代码的安全系统保护。
 * 给你两个长度为 n 的整数数组 nums 和 colors，其中 nums[i] 是第 i 间房屋中的金额，而 colors[i] 是该房屋的颜色代码。
 * 如果两间 相邻 的房屋具有 相同 的颜色代码，则你 不能同时偷窃 它们。
 * 返回你能偷窃到的 最大 金额。
 * 1 <= n == nums.length == colors.length <= 10^5
 * 1 <= nums[i], colors[i] <= 10^5
 */
public class Solution {

    public long rob(int[] nums, int[] colors) {
        int n = nums.length;
        long[] f = new long[n + 1];
        f[1] = nums[0];
        for (int i = 1; i < n; i++) {
            if (colors[i] != colors[i - 1]) {
                f[i + 1] = f[i] + nums[i];
            } else {
                f[i + 1] = Math.max(f[i - 1] + nums[i], f[i]);
            }
        }
        return f[n];
    }

    public long rob1(int[] nums, int[] colors) {
        int n = nums.length;
        long f0 = 0, f1 = nums[0];
        for (int i = 1; i < n; i++) {
            if (colors[i] != colors[i - 1]) {
                f0 = f1;
                f1 += nums[i];
            } else {
                long temp = f1;
                f1 = Math.max(f0 + nums[i], f1);
                f0 = temp;
            }
        }
        return f1;
    }

}
