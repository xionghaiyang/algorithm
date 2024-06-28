package com.sean.leetcode.LeetCode2845;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-05-22 12:16
 * @Description https://leetcode.cn/problems/count-of-interesting-subarrays/
 * 2845. 统计趣味子数组的数目
 * 给你一个下标从 0 开始的整数数组 nums ，以及整数 modulo 和整数 k 。
 * 请你找出并统计数组中 趣味子数组 的数目。
 * 如果 子数组 nums[l..r] 满足下述条件，则称其为 趣味子数组 ：
 * 在范围 [l, r] 内，设 cnt 为满足 nums[i] % modulo == k 的索引 i 的数量。
 * 并且 cnt % modulo == k 。
 * 以整数形式表示并返回趣味子数组的数目。
 * 注意：子数组是数组中的一个连续非空的元素序列。
 */
public class Solution {

    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        int[] preSum = new int[n + 1];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        long res = 0;
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + (nums.get(i) % modulo == k ? 1 : 0);
            res += map.getOrDefault((preSum[i + 1] % modulo - k + modulo) % modulo, 0);
            map.put(preSum[i + 1] % modulo, map.getOrDefault(preSum[i + 1] % modulo, 0) + 1);
        }
        return res;
    }

}
