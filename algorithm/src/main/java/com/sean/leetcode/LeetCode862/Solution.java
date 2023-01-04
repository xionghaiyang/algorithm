package com.sean.leetcode.LeetCode862;

import java.util.LinkedList;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-26 08:24
 * @Description: https://leetcode.cn/problems/shortest-subarray-with-sum-at-least-k/
 * 862. 和至少为 K 的最短子数组
 * 给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，并返回该子数组的长度。
 * 如果不存在这样的 子数组 ，返回 -1 。
 * 子数组 是数组中 连续 的一部分。
 */
public class Solution {

    public int shortestSubarray1(int[] nums, int k) {
        int n = nums.length;
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + (long) nums[i];
        }
        int res = n + 1;
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i <= n; i++) {
            while (!linkedList.isEmpty() && preSum[i] - preSum[linkedList.peekFirst()] >= k) {
                res = Math.min(res, i - linkedList.pollFirst());
            }
            while (!linkedList.isEmpty() && preSum[linkedList.peekLast()] >= preSum[i]) {
                linkedList.pollLast();
            }
            linkedList.addLast(i);
        }
        return res < n + 1 ? res : -1;
    }

}
