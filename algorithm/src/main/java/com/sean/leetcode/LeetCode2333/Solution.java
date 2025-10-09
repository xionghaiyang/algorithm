package com.sean.leetcode.LeetCode2333;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-26 13:43
 * @Description: https://leetcode.cn/problems/minimum-sum-of-squared-difference
 * 2333. 最小差值平方和
 * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，长度为 n 。
 * 数组 nums1 和 nums2 的 差值平方和 定义为所有满足 0 <= i < n 的 (nums1[i] - nums2[i])^2 之和。
 * 同时给你两个正整数 k1 和 k2 。
 * 你可以将 nums1 中的任意元素 +1 或者 -1 至多 k1 次。
 * 类似的，你可以将 nums2 中的任意元素 +1 或者 -1 至多 k2 次。
 * 请你返回修改数组 nums1 至多 k1 次且修改数组 nums2 至多 k2 次后的最小 差值平方和 。
 * 注意：你可以将数组中的元素变成 负 整数。
 * n == nums1.length == nums2.length
 * 1 <= n <= 10^5
 * 0 <= nums1[i], nums2[i] <= 10^5
 * 0 <= k1, k2 <= 10^9
 */
public class Solution {

    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int k = k1 + k2;
        long res = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            nums1[i] = Math.abs(nums1[i] - nums2[i]);
            sum += nums1[i];
            res += (long) nums1[i] * nums1[i];
        }
        if (sum <= k) {
            //所有nums[i]均可以为0
            return 0;
        }
        Arrays.sort(nums1);
        for (int i = n - 1; ; i--) {
            int m = n - i;
            long v = nums1[i], c = m * (v - (i > 0 ? nums1[i - 1] : 0));
            res -= v * v;
            if (c < k) {
                k -= c;
                continue;
            }
            v -= k / m;
            return res + k % m * (v - 1) * (v - 1) + (m - k % m) * v * v;
        }
    }

}
