package com.sean.leetcode.LeetCode2364;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-04-18 07:19
 * @Description https://leetcode.cn/problems/count-number-of-bad-pairs
 * 2364. 统计坏数对的数目
 * 给你一个下标从 0 开始的整数数组 nums 。
 * 如果 i < j 且 j - i != nums[j] - nums[i] ，那么我们称 (i, j) 是一个 坏数对 。
 * 请你返回 nums 中 坏数对 的总数目。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 */
public class Solution {

    public long countBadPairs(int[] nums) {
        int n = nums.length;
        long res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums[i] - i, map.getOrDefault(nums[i] - i, 0) + 1);
        }
        for (int cnt : map.values()) {
            res += (long) cnt * (n - cnt);
        }
        return res >> 1;
    }

    public long countBadPairs1(int[] nums) {
        int n = nums.length;
        long res = (long) n * (n - 1) / 2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i] - i;
            int c = map.getOrDefault(x, 0);
            res -= c;
            map.put(x, c + 1);
        }
        return res;
    }

}
