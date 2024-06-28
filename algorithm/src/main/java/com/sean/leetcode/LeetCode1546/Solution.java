package com.sean.leetcode.LeetCode1546;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2024-05-10 18:27
 * @Description https://leetcode.cn/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target/
 * 1546. 和为目标值且不重叠的非空子数组的最大数目
 * 给你一个数组 nums 和一个整数 target 。
 * 请你返回 非空不重叠 子数组的最大数目，且每个子数组中数字和都为 target 。
 */
public class Solution {

    public int maxNonOverlapping(int[] nums, int target) {
        int res = 0, i = 0, n = nums.length;
        while (i < n) {
            Set<Integer> set = new HashSet<Integer>() {{
                add(0);
            }};
            int preSum = 0;
            while (i < n) {
                preSum += nums[i];
                if (set.contains(preSum - target)) {
                    res++;
                    break;
                } else {
                    set.add(preSum);
                    i++;
                }
            }
            i++;
        }
        return res;
    }

}
