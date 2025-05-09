package com.sean.leetcode.LeetCode2918;

/**
 * @Author xionghaiyang
 * @Date 2025-05-10 06:56
 * @Description https://leetcode.cn/problems/minimum-equal-sum-of-two-arrays-after-replacing-zeros
 * 2918. 数组的最小相等和
 * 给你两个由正整数和 0 组成的数组 nums1 和 nums2 。
 * 你必须将两个数组中的 所有 0 替换为 严格 正整数，并且满足两个数组中所有元素的和 相等 。
 * 返回 最小 相等和 ，如果无法使两数组相等，则返回 -1 。
 * 1 <= nums1.length, nums2.length <= 10^5
 * 0 <= nums1[i], nums2[i] <= 10^6
 */
public class Solution {

    public long minSum(int[] nums1, int[] nums2) {
        int zeros1 = 0, zeros2 = 0;
        long sum1 = 0, sum2 = 0;
        for (int num : nums1) {
            if (num == 0) {
                zeros1++;
            }
            sum1 += num;
        }
        for (int num : nums2) {
            if (num == 0) {
                zeros2++;
            }
            sum2 += num;
        }
        long min1 = zeros1 + sum1;
        long min2 = zeros2 + sum2;
        if ((zeros1 == 0 && sum1 < min2) || (zeros2 == 0 && sum2 < min1)) {
            return -1;
        }
        return Math.max(min1, min2);
    }

}
