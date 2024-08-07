package com.sean.leetcode.LeetCode3131;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-08-08 07:24
 * @Description https://leetcode.cn/problems/find-the-integer-added-to-array-i/
 * 3131. 找出与数组相加的整数 I
 * 给你两个长度相等的数组 nums1 和 nums2。
 * 数组 nums1 中的每个元素都与变量 x 所表示的整数相加。
 * 如果 x 为负数，则表现为元素值的减少。
 * 在与 x 相加后，nums1 和 nums2 相等 。
 * 当两个数组中包含相同的整数，并且这些整数出现的频次相同时，两个数组 相等 。
 * 返回整数 x 。
 * 1 <= nums1.length == nums2.length <= 100
 * 0 <= nums1[i], nums2[i] <= 1000
 * 测试用例以这样的方式生成：存在一个整数 x，使得 nums1 中的每个元素都与 x 相加后，nums1 与 nums2 相等。
 */
public class Solution {

    public int addedInteger(int[] nums1, int[] nums2) {
        return Arrays.stream(nums2).min().getAsInt() - Arrays.stream(nums1).min().getAsInt();
    }

    public int addedInteger1(int[] nums1, int[] nums2) {
        int sum = 0;
        int n = nums1.length;
        for (int i = 0; i < n; i++) {
            sum += nums2[i] - nums1[i];
        }
        return sum / n;
    }

}
