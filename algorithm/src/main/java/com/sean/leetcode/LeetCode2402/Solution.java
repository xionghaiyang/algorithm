package com.sean.leetcode.LeetCode2402;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author xionghaiyang
 * @Date 2025-12-27 10:27
 * @Description https://leetcode.cn/problems/meeting-rooms-iii
 * 2402. 会议室 III
 * 给你一个整数 n ，共有编号从 0 到 n - 1 的 n 个会议室。
 * 给你一个二维整数数组 meetings ，其中 meetings[i] = [starti, endi] 表示一场会议将会在 半闭 时间区间 [starti, endi) 举办。
 * 所有 starti 的值 互不相同 。
 * 会议将会按以下方式分配给会议室：
 * 每场会议都会在未占用且编号 最小 的会议室举办。
 * 如果没有可用的会议室，会议将会延期，直到存在空闲的会议室。
 * 延期会议的持续时间和原会议持续时间 相同 。
 * 当会议室处于未占用状态时，将会优先提供给原 开始 时间更早的会议。
 * 返回举办最多次会议的房间 编号 。
 * 如果存在多个房间满足此条件，则返回编号 最小 的房间。
 * 半闭区间 [a, b) 是 a 和 b 之间的区间，包括 a 但 不包括 b 。
 * 1 <= n <= 100
 * 1 <= meetings.length <= 10^5
 * meetings[i].length == 2
 * 0 <= starti < endi <= 5 * 10^5
 * starti 的所有值 互不相同
 */
public class Solution {

    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        //会议室编号
        PriorityQueue<Integer> room = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            room.offer(i);
        }
        //(结束时间，会议室编号)
        PriorityQueue<long[]> using = new PriorityQueue<>((a, b) -> a[0] != b[0] ? Long.compare(a[0], b[0]) : Long.compare(a[1], b[1]));
        int[] cnt = new int[n];
        for (int[] meeting : meetings) {
            long start = meeting[0], end = meeting[1];
            while (!using.isEmpty() && using.peek()[0] <= start) {
                room.offer((int) using.poll()[1]);
            }
            int i;
            if (!room.isEmpty()) {//有空闲的会议室
                i = room.poll();
            } else {//没有空闲的会议室
                //弹出最早结束的会议室(若有多个同时结束，弹出编号最小的会议室)
                long[] p = using.poll();
                end += p[0] - start;
                i = (int) p[1];
            }
            using.offer(new long[]{end, i});
            cnt[i]++;
        }
        int res = 0;
        for (int i = 1; i < n; i++) {
            if (cnt[i] > cnt[res]) {
                res = i;
            }
        }
        return res;
    }

}
