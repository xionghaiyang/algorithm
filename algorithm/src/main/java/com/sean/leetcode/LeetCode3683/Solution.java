package com.sean.leetcode.LeetCode3683;

/**
 * @Author xionghaiyang
 * @Date 2025-09-14 18:17
 * @Description https://leetcode.cn/problems/earliest-time-to-finish-one-task
 * 3683. 完成一个任务的最早时间
 * 给你一个二维整数数组 tasks，其中 tasks[i] = [si, ti]。
 * 数组中的每个 [si, ti] 表示一个任务，该任务的开始时间为 si，完成该任务需要 ti 个时间单位。
 * 返回至少完成一个任务的最早时间。
 * 1 <= tasks.length <= 100
 * tasks[i] = [si, ti]
 * 1 <= si, ti <= 100
 */
public class Solution {

    public int earliestTime(int[][] tasks) {
        int n = tasks.length;
        int res = tasks[0][0] + tasks[0][1];
        for (int i = 1; i < n; i++) {
            res = Math.min(res, tasks[i][0] + tasks[i][1]);
        }
        return res;
    }

}
