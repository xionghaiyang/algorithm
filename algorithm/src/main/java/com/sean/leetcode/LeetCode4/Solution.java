package com.sean.leetcode.LeetCode4;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-17 12:23
 * @Description: https://leetcode.cn/problems/median-of-two-sorted-arrays
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10^6 <= nums1[i], nums2[i] <= 10^6
 */
public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int total = m + n;
        int midIndex = total >> 1;
        if ((total & 1) == 1) {
            return getKthElement(nums1, nums2, midIndex + 1);
        } else {
            return (getKthElement(nums1, nums2, midIndex) + getKthElement(nums1, nums2, midIndex + 1)) / 2.0;
        }
    }

    private int getKthElement(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
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
            int half = k >> 1;
            int newIndex1 = Math.min(index1 + half, m) - 1;
            int newIndex2 = Math.min(index2 + half, n) - 1;
            if (nums1[newIndex1] <= nums2[newIndex2]) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }

    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int m = nums1.length, n = nums2.length;
        int left = 0, right = m;
        //前一部部分的最大值，和后一部分的最小值
        int leftMax = 0, rightMin = 0;
        while (left <= right) {
            //前一部分包含nums1[0..i-1]和nums2[0..j-1]
            //后一部分包含nums1[i..m-1]和nums2[j..n-1]
            int i = left + ((right - left) >> 1);
            int j = (m + n + 1) / 2 - i;
            int num1_min = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int num2_min = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int num1_max = i == m ? Integer.MAX_VALUE : nums1[i];
            int num2_max = j == n ? Integer.MAX_VALUE : nums2[j];
            if (num1_min <= num2_max) {
                leftMax = Math.max(num1_min, num2_min);
                rightMin = Math.min(num1_max, num2_max);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }
        return ((m + n) & 1) == 0 ? (leftMax + rightMin) / 2.0 : leftMax;
    }

}
