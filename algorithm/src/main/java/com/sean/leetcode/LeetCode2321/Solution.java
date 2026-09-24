package com.sean.leetcode.LeetCode2321;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-24 18:08
 * @Description: https://leetcode.cn/problems/maximum-score-of-spliced-array
 * 2321. 拼接数组的最大分数
 * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，长度都是 n 。
 * 你可以选择两个整数 left 和 right ，其中 0 <= left <= right < n ，接着 交换 两个子数组 nums1[left...right] 和 nums2[left...right] 。
 * 例如，设 nums1 = [1,2,3,4,5] 和 nums2 = [11,12,13,14,15] ，整数选择 left = 1 和 right = 2，那么 nums1 会变为 [1,12,13,4,5] 而 nums2 会变为 [11,2,3,14,15] 。
 * 你可以选择执行上述操作 一次 或不执行任何操作。
 * 数组的 分数 取 sum(nums1) 和 sum(nums2) 中的最大值，其中 sum(arr) 是数组 arr 中所有元素之和。
 * 返回 可能的最大分数 。
 * 子数组 是数组中连续的一个元素序列。
 * arr[left...right] 表示子数组包含 nums 中下标 left 和 right 之间的元素（含 下标 left 和 right 对应元素）。
 */
public class Solution {

    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int sum1 = 0, f1 = 0, max1 = 0, sum2 = 0, f2 = 0, max2 = 0;
        for (int i = 0; i < n; i++) {
            int diff = nums1[i] - nums2[i];
            sum1 += nums1[i];
            f1 = Math.max(f1, 0) - diff;
            max1 = Math.max(max1, f1);
            sum2 += nums2[i];
            f2 = Math.max(f2, 0) + diff;
            max2 = Math.max(max2, f2);
        }
        return Math.max(sum1 + max1, sum2 + max2);
    }

}
