package com.sean.leetcode.LeetCode4038;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-30 17:20
 * @Description: https://leetcode.cn/problems/count-integers-appearing-in-a-single-block
 * 4038. 统计特殊整数个数
 * 给你一个整数数组 nums。
 * 如果整数 x 在 nums 中的所有出现位置都位于同一个 连续 区间内，则称 x 为 特殊整数。
 * 返回 nums 中 不同 特殊整数的数量。
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 */
public class Solution {

    public int countSpecialIntegers(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> pos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            pos.computeIfAbsent(nums[i], e -> new ArrayList<>()).add(i);
        }
        int res = 0;
        for (List<Integer> list : pos.values()) {
            if (list.get(list.size() - 1) - list.get(0) + 1 == list.size()) {
                res++;
            }
        }
        return res;
    }

}
