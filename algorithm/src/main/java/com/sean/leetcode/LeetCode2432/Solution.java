package com.sean.leetcode.LeetCode2432;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-05 08:20
 * @Description: https://leetcode.cn/problems/the-employee-that-worked-on-the-longest-task/
 * 2432. 处理用时最长的那个任务的员工
 * 共有 n 位员工，每位员工都有一个从 0 到 n - 1 的唯一 id 。
 * 给你一个二维整数数组 logs ，其中 logs[i] = [idi, leaveTimei] ：
 * idi 是处理第 i 个任务的员工的 id ，且
 * leaveTimei 是员工完成第 i 个任务的时刻。所有 leaveTimei 的值都是 唯一 的。
 * 注意，第 i 个任务在第 (i - 1) 个任务结束后立即开始，且第 0 个任务从时刻 0 开始。
 * 返回处理用时最长的那个任务的员工的 id 。如果存在两个或多个员工同时满足，则返回几人中 最小 的 id 。
 */
public class Solution {

    public int hardestWorker(int n, int[][] logs) {
        int res = logs[0][0];
        int maxcost = logs[0][1];
        int m = logs.length;
        for (int i = 1; i < m; i++) {
            int id = logs[i][0];
            int cost = logs[i][1] - logs[i - 1][1];
            if (cost > maxcost || (cost == maxcost && id < res)) {
                res = id;
                maxcost = cost;
            }
        }
        return res;
    }

}
