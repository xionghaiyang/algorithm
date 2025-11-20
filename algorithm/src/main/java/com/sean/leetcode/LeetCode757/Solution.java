package com.sean.leetcode.LeetCode757;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-11-20 09:54
 * @Description https://leetcode.cn/problems/set-intersection-size-at-least-two
 * 757. 设置交集大小至少为2
 * 给你一个二维整数数组 intervals ，其中 intervals[i] = [starti, endi] 表示从 starti 到 endi 的所有整数，包括 starti 和 endi 。
 * 包含集合 是一个名为 nums 的数组，并满足 intervals 中的每个区间都 至少 有 两个 整数在 nums 中。
 * 例如，如果 intervals = [[1,3], [3,7], [8,9]] ，那么 [1,2,4,7,8,9] 和 [2,3,4,8,9] 都符合 包含集合 的定义。
 * 返回包含集合可能的最小大小。
 * 1 <= intervals.length <= 3000
 * intervals[i].length == 2
 * 0 <= starti < endi <= 10^8
 */
public class Solution {

    public int intersectionSizeTwo(int[][] intervals) {
        int n = intervals.length, m = 2;
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });
        int res = 0;
        List<Integer>[] temp = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            temp[i] = new ArrayList<>();
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = intervals[i][0], k = temp[i].size(); k < m; j++, k++) {
                res++;
                help(intervals, temp, i - 1, j);
            }
        }
        return res;
    }

    private void help(int[][] intervals, List<Integer>[] temp, int pos, int num) {
        for (int i = pos; i >= 0; i--) {
            if (intervals[i][1] < num) {
                break;
            }
            temp[i].add(num);
        }
    }

}
