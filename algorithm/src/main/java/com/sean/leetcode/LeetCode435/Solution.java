package com.sean.leetcode.LeetCode435;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-24 21:26
 * @Description: https://leetcode.cn/problems/non-overlapping-intervals/description/
 * 435. 无重叠区间
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。
 * 返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 */
public class Solution {

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] < o2[1]) {
                    return -1;
                } else if (o1[1] > o2[1]) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        int n = intervals.length;
        int right = intervals[0][1];
        int res = 1;
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= right) {
                res++;
                right = intervals[i][1];
            }
        }
        return n - res;
    }

}
