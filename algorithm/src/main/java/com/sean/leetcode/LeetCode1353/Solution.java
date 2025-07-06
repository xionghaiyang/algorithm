package com.sean.leetcode.LeetCode1353;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author xionghaiyang
 * @Date 2025-07-07 06:32
 * @Description https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended
 * 1353. 最多可以参加的会议数目
 * 给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于 endDayi 。
 * 你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。
 * 在任意一天 d 中只能参加一场会议。
 * 请你返回你可以参加的 最大 会议数目。
 * 1 <= events.length <= 10^5
 * events[i].length == 2
 * 1 <= startDayi <= endDayi <= 10^5
 */
public class Solution {

    public int maxEvents(int[][] events) {
        int n = events.length;
        int maxDay = 0;
        for (int[] event : events) {
            maxDay = Math.max(maxDay, event[1]);
        }
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int res = 0;
        for (int i = 1, j = 0; i <= maxDay; i++) {
            while (j < n && events[j][0] <= i) {
                heap.offer(events[j++][1]);
            }
            while (!heap.isEmpty() && heap.peek() < i) {
                heap.poll();
            }
            if (!heap.isEmpty()) {
                heap.poll();
                res++;
            }
        }
        return res;
    }

}
