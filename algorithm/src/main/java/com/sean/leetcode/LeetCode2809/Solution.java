package com.sean.leetcode.LeetCode2809;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-19 12:05
 * @Description: https://leetcode.cn/problems/minimum-time-to-make-array-sum-at-most-x/
 * 2809. 使数组和小于等于 x 的最少时间
 * 给你两个长度相等下标从 0 开始的整数数组 nums1 和 nums2 。
 * 每一秒，对于所有下标 0 <= i < nums1.length ，nums1[i] 的值都增加 nums2[i] 。
 * 操作 完成后 ，你可以进行如下操作：
 * 选择任一满足 0 <= i < nums1.length 的下标 i ，并使 nums1[i] = 0 。
 * 同时给你一个整数 x 。
 * 请你返回使 nums1 中所有元素之和 小于等于 x 所需要的 最少 时间，如果无法实现，那么返回 -1 。
 */
public class Solution {

    public int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
        int n = nums1.size();
        int s1 = 0, s2 = 0;
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            int a = nums1.get(i);
            int b = nums2.get(i);
            pairs[i][0] = a;
            pairs[i][1] = b;
            s1 += a;
            s2 += b;
        }
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        int[] f = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int a = pairs[i][0];
            int b = pairs[i][1];
            for (int j = i + 1; j > 0; j--) {
                f[j] = Math.max(f[j], f[j - 1] + a + b * j);
            }
        }
        for (int t = 0; t <= n; t++) {
            if (s1 + s2 * t - f[t] <= x) {
                return t;
            }
        }
        return -1;
    }

}
