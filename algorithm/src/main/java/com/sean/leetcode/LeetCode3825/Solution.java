package com.sean.leetcode.LeetCode3825;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-28 18:02
 * @Description: https://leetcode.cn/problems/longest-strictly-increasing-subsequence-with-non-zero-bitwise-and
 * 3825. 按位与结果非零的最长上升子序列
 * 给你一个整数数组 nums。
 * 返回 nums 中按位 与（AND） 结果为 非零 的 最长严格递增子序列 的长度。
 * 如果不存在这样的 子序列，返回 0。
 * 子序列 是指从另一个数组中删除一些或不删除元素，且不改变剩余元素顺序而得到的 非空 数组。
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 */
public class Solution {

    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int w = 32 - Integer.numberOfLeadingZeros(max);
        int res = 0;
        int[] f = new int[n];
        for (int i = 0; i < w; i++) {
            int size = 0;
            for (int x : nums) {
                if ((x >> i & 1) == 0) {
                    continue;
                }
                int left = 0, right = size - 1, j = -1;
                while (left <= right) {
                    int mid = left + ((right - left) >> 1);
                    if (f[mid] < x) {
                        j = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                f[j + 1] = x;
                if (j + 1 == size) {
                    size++;
                }
            }
            res = Math.max(res, size);
        }
        return res;
    }

}
