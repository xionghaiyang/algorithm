package com.sean.leetcode.LeetCode3440;

/**
 * @Author xionghaiyang
 * @Date 2025-07-10 05:31
 * @Description https://leetcode.cn/problems/reschedule-meetings-for-maximum-free-time-ii
 * 3440. 重新安排会议得到最多空余时间 II
 * 给你一个整数 eventTime 表示一个活动的总时长，这个活动开始于 t = 0 ，结束于 t = eventTime 。
 * 同时给你两个长度为 n 的整数数组 startTime 和 endTime 。
 * 它们表示这次活动中 n 个时间 没有重叠 的会议，其中第 i 个会议的时间为 [startTime[i], endTime[i]] 。
 * 你可以重新安排 至多 一个会议，安排的规则是将会议时间平移，且保持原来的 会议时长 ，你的目的是移动会议后 最大化 相邻两个会议之间的 最长 连续空余时间。
 * 请你返回重新安排会议以后，可以得到的 最大 空余时间。
 * 注意，会议 不能 安排到整个活动的时间以外，且会议之间需要保持互不重叠。
 * 注意：重新安排会议以后，会议之间的顺序可以发生改变。
 * 1 <= eventTime <= 10^9
 * n == startTime.length == endTime.length
 * 2 <= n <= 10^5
 * 0 <= startTime[i] < endTime[i] <= eventTime
 * endTime[i] <= startTime[i + 1] 其中 i 在范围 [0, n - 2] 之间。
 */
public class Solution {

    private int eventTime;
    private int[] startTime;
    private int[] endTime;
    private int n;

    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        this.eventTime = eventTime;
        this.startTime = startTime;
        this.endTime = endTime;
        n = startTime.length;
        //有n+1个空位，计算前三大空位在哪
        int first = 0, second = -1, third = -1;
        for (int i = 1; i <= n; i++) {
            int size = get(i);
            if (size > get(first)) {
                third = second;
                second = first;
                first = i;
            } else if (second < 0 || size > get(second)) {
                third = second;
                second = i;
            } else if (third < 0 || size > get(third)) {
                third = i;
            }
        }
        int res = 0;
        //枚举桌子
        for (int i = 0; i < n; i++) {
            int size = endTime[i] - startTime[i];
            if ((i != first && i + 1 != first && size <= get(first)) || (i != second && i + 1 != second && size <= get(second)) || size <= get(third)) {
                res = Math.max(res, get(i) + size + get(i + 1));
            } else {
                res = Math.max(res, get(i) + get(i + 1));
            }
        }
        return res;
    }

    private int get(int i) {
        if (i == 0) {
            return startTime[0];
        }
        if (i == n) {
            return eventTime - endTime[n - 1];
        }
        return startTime[i] - endTime[i - 1];
    }

}
