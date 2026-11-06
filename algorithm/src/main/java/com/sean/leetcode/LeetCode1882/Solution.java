package com.sean.leetcode.LeetCode1882;

import java.util.PriorityQueue;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-07 06:01
 * @Description: https://leetcode.cn/problems/process-tasks-using-servers
 * 1882. 使用服务器处理任务
 * 给你两个 下标从 0 开始 的整数数组 servers 和 tasks ，长度分别为 n​​​​​​ 和 m​​​​​​ 。
 * servers[i] 是第 i​​​​​​​​​​ 台服务器的 权重 ，而 tasks[j] 是处理第 j​​​​​​ 项任务 所需要的时间（单位：秒）。
 * 你正在运行一个仿真系统，在处理完所有任务后，该系统将会关闭。
 * 每台服务器只能同时处理一项任务。
 * 第 0 项任务在第 0 秒可以开始处理，相应地，第 j 项任务在第 j 秒可以开始处理。
 * 处理第 j 项任务时，你需要为它分配一台 权重最小 的空闲服务器。
 * 如果存在多台相同权重的空闲服务器，请选择 下标最小 的服务器。
 * 如果一台空闲服务器在第 t 秒分配到第 j 项任务，那么在 t + tasks[j] 时它将恢复空闲状态。
 * 如果没有空闲服务器，则必须等待，直到出现一台空闲服务器，并 尽可能早 地处理剩余任务。
 * 如果有多项任务等待分配，则按照 下标递增 的顺序完成分配。
 * 如果同一时刻存在多台空闲服务器，可以同时将多项任务分别分配给它们。
 * 构建长度为 m 的答案数组 ans ，其中 ans[j] 是第 j 项任务分配的服务器的下标。
 * 返回答案数组 ans​​​​ 。
 * servers.length == n
 * tasks.length == m
 * 1 <= n, m <= 2 * 10^5
 * 1 <= servers[i], tasks[j] <= 2 * 10^5
 */
public class Solution {

    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length, m = tasks.length;
        PriorityQueue<int[]> idle = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        for (int i = 0; i < n; i++) {
            idle.offer(new int[]{servers[i], i});
        }
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int t = 0;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            t = Math.max(t, i);
            release(idle, busy, servers, t);
            if (idle.isEmpty()) {
                t = busy.peek()[0];
                release(idle, busy, servers, t);
            }
            int[] arr = idle.poll();
            arr[0] = t + tasks[i];
            busy.offer(arr);
            res[i] = arr[1];
        }
        return res;
    }

    //busy --> idle
    private void release(PriorityQueue<int[]> idle, PriorityQueue<int[]> busy, int[] servers, int t) {
        while (!busy.isEmpty() && busy.peek()[0] <= t) {
            int[] arr = busy.poll();
            arr[0] = servers[arr[1]];
            idle.offer(arr);
        }
    }

}
