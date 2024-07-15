package com.sean.leetcode.LeetCode2956;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2024-07-16 06:15
 * @Description https://leetcode.cn/problems/find-common-elements-between-two-arrays/
 * 2956. 找到两个数组中的公共元素
 * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，它们分别含有 n 和 m 个元素。
 * 请你计算以下两个数值：
 * 统计 0 <= i < n 中的下标 i ，满足 nums1[i] 在 nums2 中 至少 出现了一次。
 * 统计 0 <= i < m 中的下标 i ，满足 nums2[i] 在 nums1 中 至少 出现了一次。
 * 请你返回一个长度为 2 的整数数组 answer ，按顺序 分别为以上两个数值。
 * n == nums1.length
 * m == nums2.length
 * 1 <= n, m <= 100
 * 1 <= nums1[i], nums2[i] <= 100
 */
public class Solution {

    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }
        int[] res = new int[2];
        for (int num : nums1) {
            if (set2.contains(num)) {
                res[0]++;
            }
        }
        for (int num : nums2) {
            if (set1.contains(num)) {
                res[1]++;
            }
        }
        return res;
    }

}
