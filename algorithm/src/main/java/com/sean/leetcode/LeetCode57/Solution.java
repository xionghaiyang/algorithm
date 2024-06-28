package com.sean.leetcode.LeetCode57;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-24 20:02
 * @Description: https://leetcode.cn/problems/insert-interval/?envType=study-plan-v2&envId=top-interview-150
 * 57. 插入区间
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 */
public class Solution {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean flag = false;
        List<int[]> list = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                if (!flag) {
                    list.add(new int[]{left, right});
                    flag = true;
                }
                list.add(interval);
            } else if (interval[1] < left) {
                list.add(interval);
            } else {
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!flag) {
            list.add(new int[]{left, right});
        }
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

}
