package com.sean.leetcode.LeetCode4067;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-27 17:50
 * @Description: https://leetcode.cn/problems/longest-subarray-with-restricted-pair-sums
 * 4067. 数对和受限的最长子数组
 * 给你一个整数数组 nums。
 * 如果不存在三个 互不相同 的下标 i、j 和 k，满足 l <= i, j, k <= r 且：
 * nums[i] + nums[j] == nums[k]
 * 则子数组 nums[l..r] 是 有效 子数组。
 * 返回 nums 中有效子数组的 最大 长度。
 * 子数组 是数组中一个连续 非空 元素序列。
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 500
 */
public class Solution {

    public int maxSubarray(int[] nums) {
        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }
        //cntS[i]两数之和为i的元素对个数
        int[] cntS = new int[max * 2 + 1];
        //cntD[i]两数之差为i的元素对个数
        int[] cntD = new int[max + 1];
        int res = 0, n = nums.length;
        for (int left = 0, right = 0; right < n; right++) {
            int x = nums[right];
            while (cntS[x] > 0 || cntD[x] > 0) {
                int y = nums[left++];
                for (int i = left; i < right; i++) {
                    int z = nums[i];
                    cntS[y + z]--;
                    cntD[Math.abs(y - z)]--;
                }
            }
            for (int i = left; i < right; i++) {
                int y = nums[i];
                cntS[x + y]++;
                cntD[Math.abs(x - y)]++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

}
