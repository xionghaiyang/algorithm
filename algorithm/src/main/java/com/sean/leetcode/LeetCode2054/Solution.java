package com.sean.leetcode.LeetCode2054;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-12-23 12:23
 * @Description https://leetcode.cn/problems/two-best-non-overlapping-events
 * 2054. 两个最好的不重叠活动
 * 给你一个下标从 0 开始的二维整数数组 events ，其中 events[i] = [startTimei, endTimei, valuei] 。
 * 第 i 个活动开始于 startTimei ，结束于 endTimei ，如果你参加这个活动，那么你可以得到价值 valuei 。
 * 你 最多 可以参加 两个时间不重叠 活动，使得它们的价值之和 最大 。
 * 请你返回价值之和的 最大值 。
 * 注意，活动的开始时间和结束时间是 包括 在活动时间内的，也就是说，你不能参加两个活动且它们之一的开始时间等于另一个活动的结束时间。
 * 更具体的，如果你参加一个活动，且结束时间为 t ，那么下一个活动必须在 t + 1 或之后的时间开始。
 * 2 <= events.length <= 10^5
 * events[i].length == 3
 * 1 <= startTimei <= endTimei <= 10^9
 * 1 <= valuei <= 10^6
 */
public class Solution {

    public class Event implements Comparable<Event> {
        int ts;
        int op;
        int val;

        public Event(int ts, int op, int val) {
            this.ts = ts;
            this.op = op;
            this.val = val;
        }

        @Override
        public int compareTo(Event that) {
            return this.ts != that.ts ? Integer.compare(this.ts, that.ts) : Integer.compare(this.op, that.op);
        }
    }

    public int maxTwoEvents(int[][] events) {
        List<Event> list = new ArrayList<>();
        for (int[] event : events) {
            list.add(new Event(event[0], 0, event[2]));
            list.add(new Event(event[1], 1, event[2]));
        }
        Collections.sort(list);
        int res = 0, bestFirst = 0;
        for (Event event : list) {
            if (event.op == 0) {
                res = Math.max(res, event.val + bestFirst);
            } else {
                bestFirst = Math.max(bestFirst, event.val);
            }
        }
        return res;
    }

}
