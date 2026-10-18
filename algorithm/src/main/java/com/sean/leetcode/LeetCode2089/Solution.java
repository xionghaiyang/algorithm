package com.sean.leetcode.LeetCode2089;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-19 06:05
 * @Description: https://leetcode.cn/problems/find-target-indices-after-sorting-array
 * 2089. 找出数组排序后的目标下标
 * 给你一个下标从 0 开始的整数数组 nums 以及一个目标元素 target 。
 * 目标下标 是一个满足 nums[i] == target 的下标 i 。
 * 将 nums 按 非递减 顺序排序后，返回由 nums 中目标下标组成的列表。
 * 如果不存在目标下标，返回一个 空 列表。
 * 返回的列表必须按 递增 顺序排列。
 * 1 <= nums.length <= 100
 * 1 <= nums[i], target <= 100
 */
public class Solution {

    public List<Integer> targetIndices(int[] nums, int target) {
        int less = 0, equal = 0;
        for (int num : nums) {
            if (num < target) {
                less++;
            } else if (num == target) {
                equal++;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < equal; i++) {
            res.add(less + i);
        }
        return res;
    }

}
