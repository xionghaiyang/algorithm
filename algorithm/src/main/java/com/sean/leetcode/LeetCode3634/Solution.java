package com.sean.leetcode.LeetCode3634;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-02-06 07:20
 * @Description https://leetcode.cn/problems/minimum-removals-to-balance-array
 * 3634. 使数组平衡的最少移除数目
 * 给你一个整数数组 nums 和一个整数 k。
 * 如果一个数组的 最大 元素的值 至多 是其 最小 元素的 k 倍，则该数组被称为是 平衡 的。
 * 你可以从 nums 中移除 任意 数量的元素，但不能使其变为 空 数组。
 * 返回为了使剩余数组平衡，需要移除的元素的 最小 数量。
 * 注意：大小为 1 的数组被认为是平衡的，因为其最大值和最小值相等，且条件总是成立。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= 10^5
 */
public class Solution {

    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int maxSave = 0;
        for (int right = 0, left = 0; right < n; right++) {
            while ((long) nums[left] * k < nums[right]) {
                left++;
            }
            maxSave = Math.max(maxSave, right - left + 1);
        }
        return n - maxSave;
    }

}
