package com.sean.leetcode.LeetCode1343;

/**
 * @Author xionghaiyang
 * @Date 2025-10-22 17:00
 * @Description https://leetcode.cn/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold
 * 1343. 大小为 K 且平均值大于等于阈值的子数组数目
 * 给你一个整数数组 arr 和两个整数 k 和 threshold 。
 * 请你返回长度为 k 且平均值大于等于 threshold 的子数组数目。
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 10^4
 * 1 <= k <= arr.length
 * 0 <= threshold <= 10^4
 */
public class Solution {

    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int res = 0;
        int n = arr.length;
        for (int i = 0, sum = 0; i < n; i++) {
            sum += arr[i];
            if (i < k - 1) {
                continue;
            }
            if (sum >= threshold * k) {
                res++;
            }
            sum -= arr[i - k + 1];
        }
        return res;
    }

}
