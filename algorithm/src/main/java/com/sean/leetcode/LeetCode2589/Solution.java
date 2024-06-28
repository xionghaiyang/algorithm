package com.sean.leetcode.LeetCode2589;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-05-15 11:17
 * @Description https://leetcode.cn/problems/minimum-time-to-complete-all-tasks/
 * 2589. 完成所有任务的最少时间
 * 你有一台电脑，它可以 同时 运行无数个任务。
 * 给你一个二维整数数组 tasks ，其中 tasks[i] = [starti, endi, durationi] 表示第 i 个任务需要在 闭区间 时间段 [starti, endi] 内运行 durationi 个整数时间点（但不需要连续）。
 * 当电脑需要运行任务时，你可以打开电脑，如果空闲时，你可以将电脑关闭。
 * 请你返回完成所有任务的情况下，电脑最少需要运行多少秒。
 */
public class Solution {

    public int findMinimumTime(int[][] tasks) {
        int n = tasks.length;
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        int[] run = new int[tasks[n - 1][1] + 1];
        int res = 0;
        for (int i = 0; i < n; i++) {
            int start = tasks[i][0], end = tasks[i][1], duration = tasks[i][2];
            for (int j = start; j <= end; j++) {
                duration -= run[j];
            }
            res += Math.max(duration, 0);
            for (int j = end; j >= 0 && duration > 0; j--) {
                if (run[j] == 0) {
                    duration--;
                    run[j] = 1;
                }
            }
        }
        return res;
    }

}
