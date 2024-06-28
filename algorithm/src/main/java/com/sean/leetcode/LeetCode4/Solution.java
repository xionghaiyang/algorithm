package com.sean.leetcode.LeetCode4;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-17 12:23
 * @Description: https://leetcode.cn/problems/median-of-two-sorted-arrays/
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 */
public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int total = m + n;
        int midIndex = total / 2;
        if ((total & 1) == 1) {
            return getKthElement(nums1, nums2, midIndex + 1);
        } else {
            return (getKthElement(nums1, nums2, midIndex) + getKthElement(nums1, nums2, midIndex + 1)) / 2.0;
        }
    }

    private int getKthElement(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int index1 = 0, index2 = 0;
        while (true) {
            if (index1 == m) {
                return nums2[index2 + k - 1];
            }
            if (index2 == n) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, m) - 1;
            int newIndex2 = Math.min(index2 + half, n) - 1;
            if (nums1[newIndex1] <= nums2[newIndex2]) {
                k -= newIndex1 - index1 + 1;
                index1 = newIndex1 + 1;
            } else {
                k -= newIndex2 - index2 + 1;
                index2 = newIndex2 + 1;
            }
        }
    }

    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays1(nums2, nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;
        //median1:前一部分的最大值
        //median2:后一部分的最小值
        int median1 = 0, median2 = 0;
        while (left <= right) {
            //前一部分包含nums1[0...i-1] 和nums2[0...j-1]
            //后一部分包含nums1[i..m-1]和nums2[j..n-1]
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;
            int nums1_min = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
            int nums2_min = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
            int nums1_max = (i == m ? Integer.MAX_VALUE : nums1[i]);
            int nums2_max = (j == n ? Integer.MAX_VALUE : nums2[j]);
            if (nums1_min <= nums2_max) {
                median1 = Math.max(nums1_min, nums2_min);
                median2 = Math.min(nums1_max, nums2_max);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }
        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }

}
