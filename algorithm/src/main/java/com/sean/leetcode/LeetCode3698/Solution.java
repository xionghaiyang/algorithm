package com.sean.leetcode.LeetCode3698;

/**
 * @Author xionghaiyang
 * @Date 2025-09-28 12:47
 * @Description https://leetcode.cn/problems/split-array-with-minimum-difference
 * 3698. 分割数组得到最小绝对差
 * 给你一个整数数组 nums。
 * 将数组 恰好 分成两个子数组 left 和 right ，使得 left 严格递增 ，right 严格递减 。
 * 返回 left 与 right 的元素和之间 绝对差值的最小可能值 。
 * 如果不存在有效的分割方案，则返回 -1 。
 * 子数组 是数组中连续的非空元素序列。
 * 当数组中每个元素都严格大于其前一个元素（如果存在）时，称该数组为严格递增。
 * 当数组中每个元素都严格小于其前一个元素（如果存在）时，称该数组为严格递减。
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 */
public class Solution {

    public long splitArray(int[] nums) {
        int n = nums.length;
        long pre = nums[0];
        int i = 1;
        while (i < n && nums[i] > nums[i - 1]) {
            pre += nums[i++];
        }
        long suf = nums[n - 1];
        int j = n - 2;
        while (j >= 0 && nums[j] > nums[j + 1]) {
            suf += nums[j--];
        }
        if (i - 1 < j) {
            return -1;
        }
        long d = pre - suf;
        if (i - 1 == j) {
            return Math.abs(d);
        }
        return Math.min(Math.abs(d + nums[i - 1]), Math.abs(d - nums[i - 1]));
    }

}
