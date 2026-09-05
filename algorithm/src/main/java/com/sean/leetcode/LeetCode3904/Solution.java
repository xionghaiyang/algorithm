package com.sean.leetcode.LeetCode3904;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-05 08:24
 * @Description: https://leetcode.cn/problems/smallest-stable-index-ii
 * 3904. 最小稳定下标 II
 * 给你一个长度为 n 的整数数组 nums 和一个整数 k。
 * 对于每个下标 i，定义它的 不稳定值 为 max(nums[0..i]) - min(nums[i..n - 1])。
 * 换句话说：
 * max(nums[0..i]) 表示从下标 0 到下标 i 的元素中的 最大值 。
 * min(nums[i..n - 1]) 表示从下标 i 到下标 n - 1 的元素中的 最小值 。
 * 如果某个下标 i 的不稳定值 小于等于 k，则称该下标为 稳定下标 。
 * 返回 最小 的稳定下标。
 * 如果不存在这样的下标，则返回 -1。
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= k <= 10^9
 */
public class Solution {

    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] min = new int[n];
        min[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            min[i] = Math.min(nums[i], min[i + 1]);
        }
        for (int i = 0, max = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            if (max - min[i] <= k) {
                return i;
            }
        }
        return -1;
    }

}
