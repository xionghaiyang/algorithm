package com.sean.leetcode.LeetCode1775;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-07 08:34
 * @Description: https://leetcode.cn/problems/equal-sum-arrays-with-minimum-number-of-operations/
 * 1775. 通过最少操作次数使数组的和相等
 * 给你两个长度可能不等的整数数组 nums1 和 nums2 。
 * 两个数组中的所有值都在 1 到 6 之间（包含 1 和 6）。
 * 每次操作中，你可以选择 任意 数组中的任意一个整数，将它变成 1 到 6 之间 任意 的值（包含 1 和 6）。
 * 请你返回使 nums1 中所有数的和与 nums2 中所有数的和相等的最少操作次数。如果无法使两个数组的和相等，请返回 -1 。
 */
public class Solution {

    public int minOperations1(int[] nums1, int[] nums2) {
        int sum1 = 0;
        for (int num : nums1) {
            sum1 += num;
        }
        int sum2 = 0;
        for (int num : nums2) {
            sum2 += num;
        }
        if (sum1 == sum2) {
            return 0;
        }
        int[] big = sum1 - sum2 > 0 ? nums1 : nums2;
        int[] small = big == nums1 ? nums2 : nums1;
        int target = Math.abs(sum1 - sum2);
        int m = big.length;
        Arrays.sort(big);
        int n = small.length;
        Arrays.sort(small);
        int i = m - 1, j = 0, res = 0;
        while (target > 0) {
            while (i >= 0 && big[i] == 1) {
                i--;
            }
            while (j < n && small[j] == 6) {
                j++;
            }
            if (i >= 0 && j < n) {
                int t1 = Math.min(target, big[i] - 1);
                int t2 = Math.min(target, 6 - small[j]);
                if (t1 >= t2) {
                    i--;
                    target -= t1;
                } else {
                    j++;
                    target -= t2;
                }
                res++;
            } else if (i >= 0) {
                target -= Math.min(target, big[i] - 1);
                i--;
                res++;
            } else if (j < n) {
                target -= Math.min(target, 6 - small[j]);
                j++;
                res++;
            } else {
                break;
            }
        }
        return target == 0 ? res : -1;
    }

    public int minOperations(int[] nums1, int[] nums2) {
        int sum1 = Arrays.stream(nums1).sum();
        int sum2 = Arrays.stream(nums2).sum();
        if (sum1 == sum2) {
            return 0;
        }
        int[] big = sum1 > sum2 ? nums1 : nums2;
        int[] small = big == nums1 ? nums2 : nums1;
        int target = Math.abs(sum1 - sum2);
        int n = big.length;
        int m = small.length;
        int[] map = new int[6];
        for (int i = 0; i < n; i++) {
            if (big[i] != 1) {
                map[big[i] - 1]++;
            }
        }
        for (int i = 0; i < m; i++) {
            if (small[i] != 6) {
                map[6 - small[i]]++;
            }
        }
        int res = 0;
        for (int i = 5; i >= 1 && target > 0; i--) {
            if (map[i] == 0) {
                continue;
            }
            int cnt1 = map[i];
            int cnt2 = target / i;
            if (cnt1 > cnt2) {
                res += cnt2;
                target -= i * cnt2;
                if (target > 0) {
                    res++;
                    target -= i;
                }
            } else if (cnt1 == cnt2) {
                res += cnt2;
                target -= i * cnt2;
            } else {
                res += cnt1;
                target -= i * cnt1;
            }
        }
        return target <= 0 ? res : -1;
    }

}
