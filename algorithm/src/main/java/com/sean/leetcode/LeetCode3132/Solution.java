package com.sean.leetcode.LeetCode3132;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-08-09 09:27
 * @Description https://leetcode.cn/problems/find-the-integer-added-to-array-ii/
 * 3132. 找出与数组相加的整数 II
 * 给你两个整数数组 nums1 和 nums2。
 * 从 nums1 中移除两个元素，并且所有其他元素都与变量 x 所表示的整数相加。
 * 如果 x 为负数，则表现为元素值的减少。
 * 执行上述操作后，nums1 和 nums2 相等 。
 * 当两个数组中包含相同的整数，并且这些整数出现的频次相同时，两个数组 相等 。
 * 返回能够实现数组相等的 最小 整数 x 。
 * 3 <= nums1.length <= 200
 * nums2.length == nums1.length - 2
 * 0 <= nums1[i], nums2[i] <= 1000
 * 测试用例以这样的方式生成：存在一个整数 x，nums1 中的每个元素都与 x 相加后，再移除两个元素，nums1 可以与 nums2 相等。
 */
public class Solution {

    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        for (int i : new int[]{2, 1, 0}) {
            int left = i + 1, right = 1;
            while (left < m && right < n) {
                if (nums1[left] - nums2[right] == nums1[i] - nums2[0]) {
                    right++;
                }
                left++;
            }
            if (right == n) {
                return nums2[0] - nums1[i];
            }
        }
        return 0;
    }

}
