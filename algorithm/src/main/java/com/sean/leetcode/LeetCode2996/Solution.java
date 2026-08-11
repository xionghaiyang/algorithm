package com.sean.leetcode.LeetCode2996;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-11 08:56
 * @Description: https://leetcode.cn/problems/smallest-missing-integer-greater-than-sequential-prefix-sum
 * 2996. 大于等于顺序前缀和的最小缺失整数
 * 给你一个下标从 0 开始的整数数组 nums 。
 * 如果一个前缀 nums[0..i] 满足对于 1 <= j <= i 的所有元素都有 nums[j] = nums[j - 1] + 1 ，那么我们称这个前缀是一个 顺序前缀 。
 * 特殊情况是，只包含 nums[0] 的前缀也是一个 顺序前缀 。
 * 请你返回 nums 中没有出现过的 最小 整数 x ，满足 x 大于等于 最长 顺序前缀的和。
 * 1 <= nums.length <= 50
 * 1 <= nums[i] <= 50
 */
public class Solution {

    public int missingInteger(int[] nums) {
        int n = nums.length;
        int sum = nums[0];
        for (int i = 1; i < n && nums[i] == nums[i - 1] + 1; i++) {
            sum += nums[i];
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        while (set.contains(sum)) {
            sum++;
        }
        return sum;
    }

}
