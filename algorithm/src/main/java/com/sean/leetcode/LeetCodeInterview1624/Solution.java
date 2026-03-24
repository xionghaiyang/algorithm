package com.sean.leetcode.LeetCodeInterview1624;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2026-03-24 15:21
 * @Description https://leetcode.cn/problems/pairs-with-sum-lcci
 * 面试题 16.24. 数对和
 * 设计一个算法，找出数组中两数之和为指定值的所有整数对。
 * 一个数只能属于一个数对。
 * nums.length <= 100000
 * -10^5 <= nums[i], target <= 10^5
 */
public class Solution {

    public List<List<Integer>> pairSums(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int num0 = target - num;
            if (map.getOrDefault(num0, 0) > 0) {
                res.add(Arrays.asList(num0, num));
                map.merge(num0, -1, Integer::sum);
            } else {
                map.merge(num, 1, Integer::sum);
            }
        }
        return res;
    }

}
