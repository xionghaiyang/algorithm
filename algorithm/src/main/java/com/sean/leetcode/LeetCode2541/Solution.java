package com.sean.leetcode.LeetCode2541;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-27 11:39
 * @Description: https://leetcode.cn/problems/minimum-operations-to-make-array-equal-ii/description/
 * 2541. 使数组中所有元素相等的最小操作数 II
 * 给你两个整数数组 nums1 和 nums2 ，两个数组长度都是 n ，再给你一个整数 k 。
 * 你可以对数组 nums1 进行以下操作：
 * 选择两个下标 i 和 j ，将 nums1[i] 增加 k ，将 nums1[j] 减少 k 。
 * 换言之，nums1[i] = nums1[i] + k 且 nums1[j] = nums1[j] - k 。
 * 如果对于所有满足 0 <= i < n 都有 num1[i] == nums2[i] ，那么我们称 nums1 等于 nums2 。
 * 请你返回使 nums1 等于 nums2 的 最少 操作数。
 * 如果没办法让它们相等，请你返回 -1 。
 */
public class Solution {

    public long minOperations(int[] nums1, int[] nums2, int k) {
        //差值的和
        long diffSum = 0L;
        //差值绝对值的和
        long diffSumAbs = 0L;
        int n = nums1.length;
        for (int i = 0; i < n; i++) {
            //求两个数组每项的差
            int diff = nums1[i] - nums2[i];
            //0的情况
            if (k == 0) {
                if (diff != 0) {
                    return -1;
                } else {
                    continue;
                }
            } else if (diff % k != 0) {
                //差值无法被操作输整除，无法相等
                return -1;
            }
            diffSum += diff;
            diffSumAbs += Math.abs(diff);
        }
        //0的情况
        if (k == 0) {
            if (diffSumAbs == 0) {
                return 0;
            } else {
                return -1;
            }
        }
        //求操作次数
        //每次操作同时增加和删除同一个数组的元素，所以差值的和必须是0
        //每次操作同时修改两个元素，所以操作次数必须是偶数倍
        if (diffSum == 0 && diffSumAbs % (k * 2) == 0) {
            return diffSumAbs / (k * 2);
        } else {
            return -1;
        }
    }

}
