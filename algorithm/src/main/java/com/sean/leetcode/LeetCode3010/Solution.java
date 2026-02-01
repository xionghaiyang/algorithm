package com.sean.leetcode.LeetCode3010;

/**
 * @Author xionghaiyang
 * @Date 2026-02-01 08:11
 * @Description https://leetcode.cn/problems/divide-an-array-into-subarrays-with-minimum-cost-i
 * 3010. 将数组分成最小总代价的子数组 I
 * 给你一个长度为 n 的整数数组 nums 。
 * 一个数组的 代价 是它的 第一个 元素。
 * 比方说，[1,2,3] 的代价是 1 ，[3,4,1] 的代价是 3 。
 * 你需要将 nums 分成 3 个 连续且没有交集 的子数组。
 * 请你返回这些子数组的 最小 代价 总和 。
 * 3 <= n <= 50
 * 1 <= nums[i] <= 50
 */
public class Solution {

    public int minimumCost(int[] nums) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int x = nums[i];
            if (x < first) {
                second = first;
                first = x;
            } else if (x < second) {
                second = x;
            }
        }
        return nums[0] + first + second;
    }

}
