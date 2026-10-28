package com.sean.leetcode.LeetCode2111;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-28 12:23
 * @Description: https://leetcode.cn/problems/minimum-operations-to-make-the-array-k-increasing
 * 2111. 使数组 K 递增的最少操作次数
 * 给你一个下标从 0 开始包含 n 个正整数的数组 arr ，和一个正整数 k 。
 * 如果对于每个满足 k <= i <= n-1 的下标 i ，都有 arr[i-k] <= arr[i] ，那么我们称 arr 是 K 递增 的。
 * 比方说，arr = [4, 1, 5, 2, 6, 2] 对于 k = 2 是 K 递增的，因为：
 * arr[0] <= arr[2] (4 <= 5)
 * arr[1] <= arr[3] (1 <= 2)
 * arr[2] <= arr[4] (5 <= 6)
 * arr[3] <= arr[5] (2 <= 2)
 * 但是，相同的数组 arr 对于 k = 1 不是 K 递增的（因为 arr[0] > arr[1]），对于 k = 3 也不是 K 递增的（因为 arr[0] > arr[3] ）。
 * 每一次 操作 中，你可以选择一个下标 i 并将 arr[i] 改成任意 正整数。
 * 请你返回对于给定的 k ，使数组变成 K 递增的 最少操作次数 。
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i], k <= arr.length
 */
public class Solution {

    public int kIncreasing(int[] arr, int k) {
        int n = arr.length, maxLen = (n + k - 1) / k;
        int res = n;
        for (int i = 0; i < k; i++) {
            int[] f = new int[maxLen + 1];
            int len = 1;
            f[len] = arr[i];
            for (int j = i + k; j < n; j += k) {
                if (arr[j] >= f[len]) {
                    f[++len] = arr[j];
                } else {
                    int left = 1, right = len, p = 0;
                    while (left <= right) {
                        int mid = left + ((right - left) >> 1);
                        if (f[mid] <= arr[j]) {
                            p = mid;
                            left = mid + 1;
                        } else {
                            right = mid - 1;
                        }
                    }
                    f[p + 1] = arr[j];
                }
            }
            res -= len;
        }
        return res;
    }

}
