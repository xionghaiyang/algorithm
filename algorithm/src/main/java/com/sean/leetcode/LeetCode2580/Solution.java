package com.sean.leetcode.LeetCode2580;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-27 11:00
 * @Description: https://leetcode.cn/problems/count-ways-to-group-overlapping-ranges/
 * 2580. 统计将重叠区间合并成组的方案数
 * 给你一个二维整数数组 ranges ，其中 ranges[i] = [starti, endi] 表示 starti 到 endi 之间（包括二者）的所有整数都包含在第 i 个区间中。
 * 你需要将 ranges 分成 两个 组（可以为空），满足：
 * 每个区间只属于一个组。
 * 两个有 交集 的区间必须在 同一个 组内。
 * 如果两个区间有至少 一个 公共整数，那么这两个区间是 有交集 的。
 * 比方说，区间 [1, 3] 和 [2, 5] 有交集，因为 2 和 3 在两个区间中都被包含。
 * 请你返回将 ranges 划分成两个组的 总方案数 。由于答案可能很大，将它对 10^9 + 7 取余 后返回。
 */
public class Solution {

    static final int MOD = 1_000_000_007;

    public int countWays(int[][] ranges) {
        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);
        int n = ranges.length;
        int res = 1;
        for (int i = 0; i < n; ) {
            int right = ranges[i][1];
            int j = i + 1;
            while (j < n && ranges[j][0] <= right) {
                right = Math.max(right, ranges[j][1]);
                j++;
            }
            res = res * 2 % MOD;
            i = j;
        }
        return res;
    }

}
