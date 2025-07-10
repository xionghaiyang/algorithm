package com.sean.leetcode.LeetCode56;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-05-31 09:04
 * @Description https://leetcode.cn/problems/merge-intervals
 * 56. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 */
public class Solution {

    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        int left = intervals[0][0], right = intervals[0][1];
        for (int i = 1; i < n; i++) {
            int start = intervals[i][0], end = intervals[i][1];
            if (start <= right) {
                right = Math.max(right, end);
            } else {
                list.add(new int[]{left, right});
                left = start;
                right = end;
            }
        }
        list.add(new int[]{left, right});
        int size = list.size();
        int[][] res = new int[size][];
        for (int i = 0; i < size; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

}
