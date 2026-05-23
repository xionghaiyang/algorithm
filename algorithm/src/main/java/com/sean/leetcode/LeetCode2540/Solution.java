package com.sean.leetcode.LeetCode2540;

/**
 * @Author: xionghaiyang
 * @Date: 2026-05-19 06:28
 * @Description: https://leetcode.cn/problems/minimum-common-value
 * 2540. 最小公共值
 * 给你两个整数数组 nums1 和 nums2 ，它们已经按非降序排序，请你返回两个数组的 最小公共整数 。
 * 如果两个数组 nums1 和 nums2 没有公共整数，请你返回 -1 。
 * 如果一个整数在两个数组中都 至少出现一次 ，那么这个整数是数组 nums1 和 nums2 公共 的。
 * 1 <= nums1.length, nums2.length <= 10^5
 * 1 <= nums1[i], nums2[j] <= 10^9
 * nums1 和 nums2 都是 非降序 的。
 */
public class Solution {

    public int getCommon(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (nums1[i] == nums2[j]) {
                return nums1[i];
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return -1;
    }

}
