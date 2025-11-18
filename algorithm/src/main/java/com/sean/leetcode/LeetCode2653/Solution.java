package com.sean.leetcode.LeetCode2653;

/**
 * @Author xionghaiyang
 * @Date 2025-11-18 19:07
 * @Description https://leetcode.cn/problems/sliding-subarray-beauty
 * 2653. 滑动子数组的美丽值
 * 给你一个长度为 n 的整数数组 nums ，请你求出每个长度为 k 的子数组的 美丽值 。
 * 一个子数组的 美丽值 定义为：如果子数组中第 x 小整数 是 负数 ，那么美丽值为第 x 小的数，否则美丽值为 0 。
 * 请你返回一个包含 n - k + 1 个整数的数组，依次 表示数组中从第一个下标开始，每个长度为 k 的子数组的 美丽值 。
 * 子数组指的是数组中一段连续 非空 的元素序列。
 * n == nums.length
 * 1 <= n <= 10^5
 * 1 <= k <= n
 * 1 <= x <= k
 * -50 <= nums[i] <= 50
 */
public class Solution {

    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        final int BASE = 50;
        int[] cnt = new int[BASE * 2 + 1];
        for (int i = 0; i < k - 1; i++) {
            cnt[nums[i] + BASE]++;
        }
        int n = nums.length;
        int[] res = new int[n - k + 1];
        for (int i = k - 1; i < n; i++) {
            cnt[nums[i] + BASE]++;
            int left = x;
            for (int j = 0; j < BASE; j++) {
                left -= cnt[j];
                if (left <= 0) {
                    res[i - k + 1] = j - BASE;
                    break;
                }
            }
            cnt[nums[i - k + 1] + BASE]--;
        }
        return res;
    }

}
