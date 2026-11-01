package com.sean.leetcode.LeetCode462;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-02 06:24
 * @Description: https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii
 * 462. 最小操作次数使数组元素相等 II
 * 给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最小操作数。
 * 在一次操作中，你可以使数组中的一个元素加 1 或者减 1 。
 * 测试用例经过设计以使答案在 32 位 整数范围内。
 * n == nums.length
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class Solution {

    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, res = 0, median = nums[n / 2];
        for (int i = 0; i < n; i++) {
            res += Math.abs(nums[i] - median);
        }
        return res;
    }

}
