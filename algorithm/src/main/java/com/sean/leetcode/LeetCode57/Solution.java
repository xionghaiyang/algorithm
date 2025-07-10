package com.sean.leetcode.LeetCode57;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-24 20:02
 * @Description: https://leetcode.cn/problems/insert-interval
 * 57. 插入区间
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * 0 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^5
 * intervals 根据 starti 按 升序 排列
 * newInterval.length == 2
 * 0 <= start <= end <= 10^5
 */
public class Solution {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        int left = newInterval[0], right = newInterval[1];
        boolean flag = false;
        for (int[] interval : intervals) {
            int start = interval[0], end = interval[1];
            if (start > right) {
                if (!flag) {
                    list.add(new int[]{left, right});
                    flag = true;
                }
                list.add(interval);
            } else if (end < left) {
                list.add(interval);
            } else {
                left = Math.min(left, start);
                right = Math.max(right, end);
            }
        }
        if (!flag) {
            list.add(new int[]{left, right});
        }
        int size = list.size();
        int[][] res = new int[size][];
        for (int i = 0; i < size; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

}
