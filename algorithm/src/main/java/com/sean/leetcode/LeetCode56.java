package com.sean.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2022/7/24 10:19
 */
public class LeetCode56 {

    /**
     * https://leetcode.cn/problems/merge-intervals/
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     */

    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o2[1] - o1[1];
                } else {
                    return o1[0] - o2[0];
                }
            }
        });
        List<int[]> list = new ArrayList<>();
        process(intervals, list, 0);
//        int[][] res = new int[list.size()][2];
//        for (int i = 0; i < list.size(); i++) {
//            res[i][0] = list.get(i)[0];
//            res[i][1] = list.get(i)[1];
//        }
//        return res;
        return list.toArray(new int[list.size()][]);
    }

    private void process(int[][] intervals, List<int[]> list, int index) {
        int left = intervals[index][0], right = intervals[index][1];
        while (index + 1 < intervals.length) {
            index++;
            if (left == intervals[index][0]) {
                continue;
            }
            if (right < intervals[index][0]) {
                list.add(new int[]{left, right});
                process(intervals, list, index);
                return;
            }
            right = Math.max(right, intervals[index][1]);
        }
        list.add(new int[]{left, right});
    }

}
