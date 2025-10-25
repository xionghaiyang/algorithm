package com.sean.leetcode.LeetCode3718;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2025-10-25 19:29
 * @Description https://leetcode.cn/problems/smallest-missing-multiple-of-k
 * 3718. 缺失的最小倍数
 * 给你一个整数数组 nums 和一个整数 k，请返回从 nums 中缺失的、最小的正整数 k 的倍数。
 * 倍数 指能被 k 整除的任意正整数。
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 * 1 <= k <= 100
 */
public class Solution {

    public int missingMultiple(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int x = k; ; x += k) {
            if (!set.contains(x)) {
                return x;
            }
        }
    }

}
