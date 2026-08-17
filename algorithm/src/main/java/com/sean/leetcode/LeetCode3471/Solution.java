package com.sean.leetcode.LeetCode3471;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-18 06:00
 * @Description: https://leetcode.cn/problems/find-the-largest-almost-missing-integer
 * 3471. 找出最大的几近缺失整数
 * 给你一个整数数组 nums 和一个整数 k 。
 * 如果整数 x 恰好仅出现在 nums 中的一个大小为 k 的子数组中，则认为 x 是 nums 中的几近缺失（almost missing）整数。
 * 返回 nums 中 最大的几近缺失 整数，如果不存在这样的整数，返回 -1 。
 * 子数组 是数组中的一个连续元素序列。
 * 1 <= nums.length <= 50
 * 0 <= nums[i] <= 50
 * 1 <= k <= nums.length
 */
public class Solution {

    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        if (k == n) {
            return Arrays.stream(nums).max().getAsInt();
        }
        if (k == 1) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.merge(num, 1, Integer::sum);
            }
            int res = -1;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 1) {
                    res = Math.max(res, entry.getKey());
                }
            }
            return res;
        }
        return Math.max(f(nums, 1, n - 1, nums[0]), f(nums, 0, n - 2, nums[n - 1]));
    }

    private int f(int[] nums, int left, int right, int num) {
        for (int i = left; i <= right; i++) {
            if (nums[i] == num) {
                return -1;
            }
        }
        return num;
    }

}
