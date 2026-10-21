package com.sean.leetcode.LeetCode3942;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-22 06:13
 * @Description: https://leetcode.cn/problems/minimum-operations-to-sort-a-permutation
 * 3942. 排序排列的最少操作数
 * 给你一个长度为 n 的整数数组 nums，其中 nums 是区间 [0..n - 1] 中所有数字的一个排列。
 * 你 只能 执行以下操作：
 * 反转 整个数组。
 * 左旋一位：将第一个元素移动到数组末尾，其余元素整体向左移动一位。
 * 返回将数组按 递增 顺序排序所需的 最少 操作次数。
 * 如果仅使用给定操作无法将数组排序，则返回 -1。
 * 排列 是数组中所有元素的一种重新排列。
 * 1 <= n == nums.length <= 10^5
 * 0 <= nums[i] <= n - 1
 * nums 是从 0 到 n - 1 的整数排列。
 */
public class Solution {

    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        int zero = 0;
        while (nums[zero] != 0) {
            zero++;
        }
        boolean asc = true, desc = true;
        for (int i = 1; i < n; i++) {
            int val = nums[(zero + i) % n];
            if (val != i) {
                asc = false;
            }
            if (val != n - i) {
                desc = false;
            }
            if (!asc && !desc) {
                return -1;
            }
        }
        return asc ? Math.min(zero, n - zero + 2) : Math.min(n - zero, zero + 2);
    }

}
