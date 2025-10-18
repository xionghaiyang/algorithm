package com.sean.leetcode.LeetCode3397;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-10-18 09:34
 * @Description https://leetcode.cn/problems/maximum-number-of-distinct-elements-after-operations
 * 3397. 执行操作后不同元素的最大数量
 * 给你一个整数数组 nums 和一个整数 k。
 * 你可以对数组中的每个元素 最多 执行 一次 以下操作：
 * 将一个在范围 [-k, k] 内的整数加到该元素上。
 * 返回执行这些操作后，nums 中可能拥有的不同元素的 最大 数量。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= k <= 10^9
 */
public class Solution {

    public int maxDistinctElements(int[] nums, int k) {
        int n = nums.length;
        if (k * 2 + 1 >= n) {
            return n;
        }
        Arrays.sort(nums);
        int res = 0;
        int pre = Integer.MIN_VALUE;
        for (int num : nums) {
            num = Math.min(Math.max(num - k, pre + 1), num + k);
            if (num > pre) {
                res++;
                pre = num;
            }
        }
        return res;
    }

}
