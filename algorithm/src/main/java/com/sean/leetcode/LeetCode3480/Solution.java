package com.sean.leetcode.LeetCode3480;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-07-26 06:54
 * @Description https://leetcode.cn/problems/maximize-subarrays-after-removing-one-conflicting-pair
 * 3480. 删除一个冲突对后最大子数组数目
 * 给你一个整数 n，表示一个包含从 1 到 n 按顺序排列的整数数组 nums。
 * 此外，给你一个二维数组 conflictingPairs，其中 conflictingPairs[i] = [a, b] 表示 a 和 b 形成一个冲突对。
 * 从 conflictingPairs 中删除 恰好 一个元素。
 * 然后，计算数组 nums 中的非空子数组数量，这些子数组都不能同时包含任何剩余冲突对 [a, b] 中的 a 和 b。
 * 返回删除 恰好 一个冲突对后可能得到的 最大 子数组数量。
 * 子数组 是数组中一个连续的 非空 元素序列。
 * 2 <= n <= 10^5
 * 1 <= conflictingPairs.length <= 2 * n
 * conflictingPairs[i].length == 2
 * 1 <= conflictingPairs[i][j] <= n
 * conflictingPairs[i][0] != conflictingPairs[i][1]
 */
public class Solution {

    public long maxSubarrays(int n, int[][] conflictingPairs) {
        int[] g0 = new int[n + 1];
        int[] g1 = new int[n + 1];
        Arrays.fill(g0, n + 1);
        Arrays.fill(g1, n + 1);
        for (int[] conflictingPair : conflictingPairs) {
            int a = Math.min(conflictingPair[0], conflictingPair[1]), b = Math.max(conflictingPair[0], conflictingPair[1]);
            if (b < g0[a]) {
                g1[a] = g0[a];
                g0[a] = b;
            } else if (b < g1[a]) {
                g1[a] = b;
            }
        }
        long res = 0, maxExtra = 0, extra = 0;
        int b0 = n + 1, b1 = n + 1;
        for (int i = n; i >= 1; i--) {
            int preB0 = b0;
            int b = g0[i];
            if (b < b0) {
                b1 = Math.min(b0, g1[i]);
                b0 = b;
            } else if (b < b1) {
                b1 = b;
            }
            res += b0 - i;
            if (b0 != preB0) {//重新统计连续相同b0的extra
                extra = 0;
            }
            extra += b1 - b0;
            maxExtra = Math.max(maxExtra, extra);
        }
        return res + maxExtra;
    }

}
