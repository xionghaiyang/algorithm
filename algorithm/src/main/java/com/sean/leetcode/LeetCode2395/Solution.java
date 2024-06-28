package com.sean.leetcode.LeetCode2395;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-03-27 08:48
 * @Description: https://leetcode.cn/problems/find-subarrays-with-equal-sum/
 * 2395. 和相等的子数组
 * 给你一个下标从 0 开始的整数数组 nums ，判断是否存在 两个 长度为 2 的子数组且它们的 和 相等。
 * 注意，这两个子数组起始位置的下标必须 不相同 。
 * 如果这样的子数组存在，请返回 true，否则返回 false 。
 * 子数组 是一个数组中一段连续非空的元素组成的序列。
 */
public class Solution {

    public boolean findSubarrays(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i + 1 < n; i++) {
            int cur = nums[i] + nums[i + 1];
            if (set.contains(cur)) {
                return true;
            } else {
                set.add(cur);
            }
        }
        return false;
    }

}
