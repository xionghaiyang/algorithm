package com.sean.leetcode.LeetCode4056;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-20 18:21
 * @Description: https://leetcode.cn/problems/number-of-intersecting-interval-pairs-i
 * 4056. 统计相交区间对 I
 * 给你一个包含 n 个元素的二维整数数组 intervals，其中 intervals[i] = [starti, endi] 表示从 starti 到 endi 的 闭区间 。
 * 返回满足 0 <= i < j < n，且 intervals[i] 与 intervals[j] 相交 的下标对 (i, j) 的数量。
 * 如果两个区间至少有一个公共点，则称它们 相交。
 * 仅共享一个端点的情况也视为相交。
 * 2 <= intervals.length <= 100
 * intervals[i] == [starti, endi]
 * 0 <= starti <= endi <= 100
 */
public class Solution {

    public int countIntersectingIntervals(int[][] intervals) {
        int n = intervals.length;
        int res = n * (n - 1) / 2;
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        for (int i = 1; i < n; i++) {
            res -= binarySearch(intervals, i - 1, intervals[i][0]);
        }
        return res;
    }

    private int binarySearch(int[][] intervals, int right, int target) {
        int res = 0, left = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (intervals[mid][1] < target) {
                res = mid + 1;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

}
