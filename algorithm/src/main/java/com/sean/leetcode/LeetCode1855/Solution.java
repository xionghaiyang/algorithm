package com.sean.leetcode.LeetCode1855;

/**
 * @Author xionghaiyang
 * @Date 2026-04-19 06:26
 * @Description https://leetcode.cn/problems/maximum-distance-between-a-pair-of-values
 * 1855. 下标对中的最大距离
 * 给你两个 非递增 的整数数组 nums1​​​​​​ 和 nums2​​​​​​ ，数组下标均 从 0 开始 计数。
 * 下标对 (i, j) 中 0 <= i < nums1.length 且 0 <= j < nums2.length 。
 * 如果该下标对同时满足 i <= j 且 nums1[i] <= nums2[j] ，则称之为 有效 下标对，该下标对的 距离 为 j - i​​ 。​​
 * 返回所有 有效 下标对 (i, j) 中的 最大距离 。
 * 如果不存在有效下标对，返回 0 。
 * 一个数组 arr ，如果每个 1 <= i < arr.length 均有 arr[i-1] >= arr[i] 成立，那么该数组是一个 非递增 数组。
 * 1 <= nums1.length <= 10^5
 * 1 <= nums2.length <= 10^5
 * 1 <= nums1[i], nums2[j] <= 10^5
 * nums1 和 nums2 都是 非递增 数组
 */
public class Solution {

    public int maxDistance(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int res = 0;
        for (int i = 0, j = 0; j < m; j++) {
            while (i < n && nums1[i] > nums2[j]) {
                i++;
            }
            if (i == n) {
                break;
            }
            res = Math.max(res, j - i);
        }
        return res;
    }

}
