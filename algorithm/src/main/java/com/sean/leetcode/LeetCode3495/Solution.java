package com.sean.leetcode.LeetCode3495;

/**
 * @Author xionghaiyang
 * @Date 2025-09-06 08:31
 * @Description https://leetcode.cn/problems/minimum-operations-to-make-array-elements-zero
 * 3495. 使数组元素都变为零的最少操作次数
 * 给你一个二维数组 queries，其中 queries[i] 形式为 [l, r]。
 * 每个 queries[i] 表示了一个元素范围从 l 到 r （包括 l 和 r ）的整数数组 nums 。
 * 在一次操作中，你可以：
 * 选择一个查询数组中的两个整数 a 和 b。
 * 将它们替换为 floor(a / 4) 和 floor(b / 4)。
 * 你的任务是确定对于每个查询，将数组中的所有元素都变为零的 最少 操作次数。返回所有查询结果的总和。
 * 1 <= queries.length <= 10^5
 * queries[i].length == 2
 * queries[i] == [l, r]
 * 1 <= l < r <= 10^9
 */
public class Solution {

    public long minOperations(int[][] queries) {
        long res = 0;
        for (int[] query : queries) {
            res += (f(query[1]) - f(query[0] - 1) + 1) / 2;
        }
        return res;
    }

    private long f(int n) {
        int m = 32 - Integer.numberOfLeadingZeros(n);
        int k = (m - 1) / 2 * 2;
        long res = ((long) k << k >> 1) - (1 << k) / 3;
        return res + (long) (m + 1) / 2 * (n + 1 - (1 << k));
    }

}
