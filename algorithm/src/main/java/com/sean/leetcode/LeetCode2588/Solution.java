package com.sean.leetcode.LeetCode2588;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-05-09 18:12
 * @Description https://leetcode.cn/problems/count-the-number-of-beautiful-subarrays/
 * 2588. 统计美丽子数组数目
 * 给你一个下标从 0 开始的整数数组nums 。每次操作中，你可以：
 * 选择两个满足 0 <= i, j < nums.length 的不同下标 i 和 j 。
 * 选择一个非负整数 k ，满足 nums[i] 和 nums[j] 在二进制下的第 k 位（下标编号从 0 开始）是 1 。
 * 将 nums[i] 和 nums[j] 都减去 2^k 。
 * 如果一个子数组内执行上述操作若干次后，该子数组可以变成一个全为 0 的数组，那么我们称它是一个 美丽 的子数组。
 * 请你返回数组 nums 中 美丽子数组 的数目。
 * 子数组是一个数组中一段连续 非空 的元素序列。
 */
public class Solution {

    public long beautifulSubarrays(int[] nums) {
        long res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
            if (map.containsKey(xor)) {
                res += map.get(xor);
            }
            map.put(xor, map.getOrDefault(xor, 0) + 1);
        }
        return res;
    }

}
