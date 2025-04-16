package com.sean.leetcode.LeetCode2537;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-04-16 08:23
 * @Description https://leetcode.cn/problems/count-the-number-of-good-subarrays
 * 2537. 统计好子数组的数目
 * 给你一个整数数组 nums 和一个整数 k ，请你返回 nums 中 好 子数组的数目。
 * 一个子数组 arr 如果有 至少 k 对下标 (i, j) 满足 i < j 且 arr[i] == arr[j] ，那么称它是一个 好 子数组。
 * 子数组 是原数组中一段连续 非空 的元素序列。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i], k <= 10^9
 */
public class Solution {

    public long countGood(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        long res = 0;
        for (int left = 0, right = -1, cur = 0; left < n; left++) {
            while (right + 1 < n && cur < k) {
                right++;
                cur += map.getOrDefault(nums[right], 0);
                map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            }
            if (cur >= k) {
                res += n - right;
            }
            map.put(nums[left], map.getOrDefault(nums[left], 0) - 1);
            cur -= map.get(nums[left]);
            if (map.get(nums[left]) == 0) {
                map.remove(nums[left]);
            }
        }
        return res;
    }

}
