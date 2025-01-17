package com.sean.leetcode.LeetCode3095;

/**
 * @Author xionghaiyang
 * @Date 2025-01-16 15:59
 * @Description https://leetcode.cn/problems/shortest-subarray-with-or-at-least-k-i/
 * 3095. 或值至少 K 的最短子数组 I
 * 给你一个 非负 整数数组 nums 和一个整数 k 。
 * 如果一个数组中所有元素的按位或运算 OR 的值 至少 为 k ，那么我们称这个数组是 特别的 。
 * 请你返回 nums 中 最短特别非空子数组的长度，如果特别子数组不存在，那么返回 -1 。
 * 1 <= nums.length <= 50
 * 0 <= nums[i] <= 50
 * 0 <= k < 64
 */
public class Solution {

    public int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int value = 0;
            for (int j = i; j < n; j++) {
                value |= nums[j];
                if (value >= k) {
                    res = Math.min(res, j - i + 1);
                    break;
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public int minimumSubarrayLength1(int[] nums, int k) {
        int n = nums.length;
        int[] bits = new int[30];
        int res = Integer.MAX_VALUE;
        for (int left = 0, right = 0; right < n; right++) {
            for (int i = 0; i < 30; i++) {
                bits[i] += (nums[right] >> i) & 1;
            }
            while (left <= right && calc(bits) >= k) {
                res = Math.min(res, right - left + 1);
                for (int i = 0; i < 30; i++) {
                    bits[i] -= (nums[left] >> i) & 1;
                }
                left++;
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int calc(int[] bits) {
        int res = 0;
        for (int i = 0; i < bits.length; i++) {
            if (bits[i] > 0) {
                res |= 1 << i;
            }
        }
        return res;
    }

}