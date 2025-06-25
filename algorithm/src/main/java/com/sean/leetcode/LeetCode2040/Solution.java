package com.sean.leetcode.LeetCode2040;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-06-25 05:52
 * @Description https://leetcode.cn/problems/kth-smallest-product-of-two-sorted-arrays
 * 2040. 两个有序数组的第 K 小乘积
 * 给你两个 从小到大排好序 且下标从 0 开始的整数数组 nums1 和 nums2 以及一个整数 k ，
 * 请你返回第 k （从 1 开始编号）小的 nums1[i] * nums2[j] 的乘积，其中 0 <= i < nums1.length 且 0 <= j < nums2.length 。
 * 1 <= nums1.length, nums2.length <= 5 * 10^4
 * -10^5 <= nums1[i], nums2[j] <= 10^5
 * 1 <= k <= nums1.length * nums2.length
 * nums1 和 nums2 都是从小到大排好序的。
 */
public class Solution {

    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        int i0 = lowerBound(nums1, 0);
        int j0 = lowerBound(nums2, 0);
        int m = nums1.length, n = nums2.length;
        List<Long> corners = Arrays.asList((long) nums1[0] * nums2[0]
                , (long) nums1[0] * nums2[n - 1]
                , (long) nums1[m - 1] * nums2[0]
                , (long) nums1[m - 1] * nums2[n - 1]);
        long left = Collections.min(corners) - 1, right = Collections.max(corners);
        while (left + 1 < right) {
            long mid = left + ((right - left) >> 1);
            if (check(nums1, nums2, i0, j0, k, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    //<=target的个数是否大于等于k
    private boolean check(int[] nums1, int[] nums2, int i0, int j0, long k, long target) {
        int m = nums1.length;
        int n = nums2.length;
        long cnt = 0;
        if (target < 0) {
            //右上区域
            int i = 0;
            int j = j0;
            while (i < i0 && j < n) {
                if ((long) nums1[i] * nums2[j] > target) {
                    j++;
                } else {
                    cnt += n - j;
                    i++;
                }
            }
            //左下区域
            i = i0;
            j = 0;
            while (i < m && j < j0) {
                if ((long) nums1[i] * nums2[j] > target) {
                    i++;
                } else {
                    cnt += m - i;
                    j++;
                }
            }
        } else {
            //右上区域和左下区域的所有数都<=0 <=target
            cnt = (long) i0 * (n - j0) + (long) (m - i0) * j0;
            //左上区域
            int i = 0;
            int j = j0 - 1;
            while (i < i0 && j >= 0) {
                if ((long) nums1[i] * nums2[j] > target) {
                    i++;
                } else {
                    cnt += i0 - i;
                    j--;
                }
            }
            //右下区域
            i = i0;
            j = n - 1;
            while (i < m && j >= j0) {
                if ((long) nums1[i] * nums2[j] > target) {
                    j--;
                } else {
                    cnt += j - j0 + 1;
                    i++;
                }
            }
        }
        return cnt >= k;
    }

    //最小的大于target的数
    private int lowerBound(int[] nums, int target) {
        int left = -1, right = nums.length;
        while (left + 1 < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

}
