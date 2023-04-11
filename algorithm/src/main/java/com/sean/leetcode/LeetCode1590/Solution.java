package com.sean.leetcode.LeetCode1590;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-03-10 08:03
 * @Description: https://leetcode.cn/problems/make-sum-divisible-by-p/
 * 1590. 使数组和能被 P 整除
 * 给你一个正整数数组 nums，请你移除 最短 子数组（可以为 空），使得剩余元素的 和 能被 p 整除。 不允许 将整个数组都移除。
 * 请你返回你需要移除的最短子数组的长度，如果无法满足题目要求，返回 -1 。
 * 子数组 定义为原数组中连续的一组元素。
 */
public class Solution {

    public int minSubarray1(int[] nums, int p) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        if (preSum[n] % p == 0) {
            return 0;
        }
        long sum = preSum[n];
        for (int len = 1; len <= n - 1; len++) {
            for (int start = 0; start + len - 1 < n; start++) {
                if ((sum - (preSum[start + len] - preSum[start])) % p == 0) {
                    return len;
                }
            }
        }
        return -1;
    }

    public int minSubarray(int[] nums, int p) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int x = 0;
        for (int num : nums) {
            x = (x + num) % p;
        }
        if (x == 0) {
            return 0;
        }
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int y = 0, res = n;
        for (int i = 0; i < n; i++) {
            map.put(y, i);
            y = (y + nums[i]) % p;
            if (map.containsKey((y - x + p) % p)) {
                res = Math.min(res, i - map.get((y - x + p) % p) + 1);
            }
        }
        return res == n ? -1 : res;
    }

}
